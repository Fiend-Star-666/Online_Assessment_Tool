package com.training.java.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.java.dto.ExamDTO;

@RestController
@RequestMapping("/api/v1/users")
public class UserControllers {

    @GetMapping("/{userId}/report")
    public ResponseEntity<String> getUserReport(@PathVariable String userId) {
        // Logic to retrieve and return the user's report
        return ResponseEntity.ok("User report retrieved successfully.");
    }

   @PostMapping("/{userId}/exams")
    public ResponseEntity<String> startNewExam(@PathVariable String userId, @RequestBody ExamDTO examDto) {
        // Logic to start a new exam for the user
        return ResponseEntity.ok("New exam started successfully.");
    }
}
