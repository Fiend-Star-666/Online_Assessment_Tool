package com.training.java.security.jwt;

import com.training.java.entities.abstrct.Account;
import com.training.java.repositories.AccountRepository;
import com.training.java.security.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;

@Service
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    AccountRepository accountRepo;

    @Autowired
    private MyUserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            System.out.println("step 0");
            String jwt = parseJwt(request);
            System.out.println("step 0.1");
            if(jwt != null && jwtUtils.validateJwtToken(jwt)) {
                System.out.println("step 0.2");
                String emailId = jwtUtils.getUserNameFromJwtToken(jwt);
                System.out.println("step 1.1");
                Account user = accountRepo.findByEmail(emailId);
                System.out.println("step 1.2");
                UserDetails userDetails = userDetailsService.loadUserByUsername(emailId);
                System.out.println("step 1.3");
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails.getUsername(),
                                userDetails.getPassword(),
                                userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                System.out.println("step 1.4");
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("step 1.5");
            }
        } catch (Exception e) {
            System.out.println("step 2.1");
            logger.error("Cannot set user authentication: {}", e);
        }
        System.out.println("step 3.1");
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        System.out.println("step 4.1");
        String headerAuth = request.getHeader("Authorization");
        System.out.println("step 4.2");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer")) {
            System.out.println("step 4.3");
            return headerAuth.substring(7, headerAuth.length());
        }
        System.out.println("step 4.4");
        return null;
    }
}
