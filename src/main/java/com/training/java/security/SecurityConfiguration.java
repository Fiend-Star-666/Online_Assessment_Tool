package com.training.java.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import java.util.Set;

import static org.springframework.security.config.Customizer.withDefaults;
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Value("${spring.security.user.name}")
    private String userName;

    @Value("${spring.security.user.password}")
    private String userPassword;

    @Value("${spring.security.user.roles}")
    private Set<String> roles;  // Use Set instead of String[]
    private AuthenticationManagerBuilder authManagerBuilder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public void authenticationManagerBuilder(UserDetailsService userDetailsService) throws Exception {
        this.authManagerBuilder = new AuthenticationManagerBuilder((ObjectPostProcessor<Object>) userDetailsService);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").hasRole("USER")
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

   /* @Bean
    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder authManagerBuilder) {
        return authManagerBuilder.getOrBuild();
    }*/



}
