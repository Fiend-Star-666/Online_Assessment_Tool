package com.training.java.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    @NotBlank
    private String language;

    @NotNull
    private Integer levelOfQuestion;
    @NotBlank
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    @NotBlank
    private String realCorrectAnswer;

}
