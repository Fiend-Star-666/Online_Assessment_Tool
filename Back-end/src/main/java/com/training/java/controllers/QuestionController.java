package com.training.java.controllers;

import com.training.java.csvHandlers.CsvToQuestionnaire;
import com.training.java.dto.QuestionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final CsvToQuestionnaire csvToQuestionnaire;

    public QuestionController(CsvToQuestionnaire csvToQuestionnaire) {
        this.csvToQuestionnaire = csvToQuestionnaire;
    }

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getQuestions(
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Integer level
    ) {
        List<QuestionDTO> questions;
        if (language != null && level != null) {
            questions = csvToQuestionnaire.getQuestionsByLanguageAndLevel(language, level);
        } else if (language != null) {
            questions = csvToQuestionnaire.getQuestionsByLanguage(language);
        } else if (level != null) {
            questions = csvToQuestionnaire.getQuestionsByLevel(level);
        } else {
            questions = csvToQuestionnaire.getRandomQuestions(10);
        }
        return ResponseEntity.ok(questions);
    }

    @PostMapping("/save-to-db")
    public ResponseEntity<String> saveQuestionsToDb() {
        try {
            csvToQuestionnaire.saveQuestionsToDb();
            return ResponseEntity.ok("Questions saved to the database.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save questions to the database.");
        }
    }

}
