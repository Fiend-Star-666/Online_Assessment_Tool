package com.training.java.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExamReportDTO {

    private Long examId;
    private String userName;
    private int level;
    private List<QuestionDTO> questions;
    private int totalQuestions;
    private int correctAnswers;

}
