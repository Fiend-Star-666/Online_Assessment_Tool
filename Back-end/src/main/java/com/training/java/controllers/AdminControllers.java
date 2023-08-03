package com.training.java.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class AdminControllers {

    @GetMapping("/reports")
    public ResponseEntity<String> viewReports(@RequestParam String filter) {
        //TODO Logic to fetch and return the student details based on the filter
        return ResponseEntity.ok("Reports fetched successfully.");
    }

    @PostMapping("/questions/{fileName}/remove")
    public ResponseEntity<String> removeQuestions(@PathVariable String fileName) {
        //TODO Logic to remove questions file based on the provided file name
        return ResponseEntity.ok("Questions removed successfully.");
    }

}
