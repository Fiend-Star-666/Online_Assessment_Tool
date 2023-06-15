package com.training.java.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AdminControllers {

    @GetMapping("/reports")
    public ResponseEntity<String> viewReports(@RequestParam String filter) {
        // Logic to fetch and return the student details based on the filter
        return ResponseEntity.ok("Reports fetched successfully.");
    }

    @PostMapping("/questions")
    public ResponseEntity<String> addQuestions(@RequestParam("file") MultipartFile file) {
        // Logic to handle uploading and adding questions from the file
        return ResponseEntity.ok("Questions added successfully.");
    }

    @PostMapping("/questions/{fileName}/remove")
    public ResponseEntity<String> removeQuestions(@PathVariable String fileName) {
        // Logic to remove questions file based on the provided file name
        return ResponseEntity.ok("Questions removed successfully.");
    }

}
