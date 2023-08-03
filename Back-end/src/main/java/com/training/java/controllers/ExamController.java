package com.training.java.controllers;

import com.training.java.dto.AnswerDTO;
import com.training.java.dto.ExamReportDTO;
import com.training.java.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping("/start")
    public ResponseEntity<String> startExam(@RequestParam("userId") Long userId, @RequestParam("level") int level) {
        boolean started = examService.startExam(userId, level);

        if (!started) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to start the exam.");
        }

        return ResponseEntity.ok("Exam started successfully.");
    }

    @GetMapping("/{examId}/report")
    public ResponseEntity<ExamReportDTO> getExamReport(@PathVariable("examId") Long examId) {
        ExamReportDTO reportDTO = examService.getExamReport(examId);

        if (reportDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(reportDTO);
    }

    @PostMapping("/{examId}/submit")
    public ResponseEntity<String> submitExam(@PathVariable("examId") Long examId, @RequestBody List<AnswerDTO> answerDTOs) {
        boolean submitted = examService.submitExam(examId, answerDTOs);

        if (!submitted) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to submit the exam.");
        }

        return ResponseEntity.ok("Exam submitted successfully.");
    }
}
