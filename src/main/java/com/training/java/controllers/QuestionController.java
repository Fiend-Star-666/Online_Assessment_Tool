package com.training.java.controllers;

import com.training.java.csvHandlers.CsvToQuestionnaire;
import com.training.java.dto.QuestionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
