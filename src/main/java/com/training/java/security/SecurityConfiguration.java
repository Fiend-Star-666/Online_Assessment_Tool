package com.training.java.security;


import com.training.java.security.jwt.AuthEntryPointJwt;
import com.training.java.security.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.stereotype.Service;


import static org.springframework.security.config.Customizer.withDefaults;
@EnableWebSecurity
@EnableMethodSecurity
@Service
public class SecurityConfiguration {

    @Autowired
    UserDetailsService userDetailsService;


    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    private AuthenticationManagerBuilder authManagerBuilder;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public void authenticationManagerBuilder(UserDetailsService userDetailsService) throws Exception {
        this.authManagerBuilder = new AuthenticationManagerBuilder(userDetailsService);
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
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").hasRole("USER")
                )
                .formLogin((form) -> form
                .loginPage("/login")
                .permitAll()
                 )
                .logout((logout) -> logout.permitAll())
                .httpBasic(withDefaults());
        return http.build();
    }

   /* @Bean
    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder authManagerBuilder) {
        return authManagerBuilder.getOrBuild();
    }*/



}
