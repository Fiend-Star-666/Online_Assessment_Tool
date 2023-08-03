package com.training.java.controllers;

import com.training.java.entities.Account;
import com.training.java.entities.enums.QualificationsEnum;
import com.training.java.entities.enums.StatesOfIndiaEnum;
import com.training.java.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/register")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> showRegistrationsForm() {
        Map<String, Object> response = new HashMap<>();
        response.put("user", new Account());
        response.put("states", StatesOfIndiaEnum.values());
        response.put("streams", QualificationsEnum.values());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<String> registerUser(Account user) {
        registrationService.registerUser(user);
        return new ResponseEntity<>("redirect:/register?success", HttpStatus.CREATED);
    }
}
