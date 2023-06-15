package com.training.java.controllers;

import com.training.java.repositories.AccountRepository;
import com.training.java.security.MyUserDetailsService;
import com.training.java.security.jpaModels.MyUserDetails;
import com.training.java.security.jwt.JwtUtils;
import com.training.java.security.payload.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.training.java.security.SecurityConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AccountRepository accountRepo;

    @Autowired
    JwtUtils jwtUtils;


    @Autowired
    private MyUserDetailsService userDetailsService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String,String> Payload ) {
        String emailId= Payload.get("email");

        String password= Payload.get("password");
        System.out.println("step1");
        AuthenticationManager authenticationManager = null;
        System.out.println("step2");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(emailId,password));
        System.out.println("step3");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("step4");
        String jwt = jwtUtils.generateJwtToken(authentication);
        System.out.println("step5");
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        System.out.println("step6");
       List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        System.out.println("step7");
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getType(),
                userDetails.getAccId(),
                userDetails.getUsername(),
                roles));
    }
}
