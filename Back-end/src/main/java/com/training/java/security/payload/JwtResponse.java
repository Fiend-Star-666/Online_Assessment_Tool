package com.training.java.security.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private int accountID;
    private String email;
    private List<String> roles;

}
