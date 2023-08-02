package com.training.java.entities;

import com.training.java.entities.enums.CodingLanguageEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "TEXT", name = "level_of_question")
    private Integer levelOfQuestion;

    @NotNull
    @Column(name = "Coding_language")
    private CodingLanguageEnum language;

    @NotBlank
    @Column(columnDefinition = "TEXT", name = "question")
    private String question;

    @Column(columnDefinition = "TEXT", name = "option_a")
    private String optionA;

    @Column(columnDefinition = "TEXT", name = "option_b")
    private String optionB;

    @Column(columnDefinition = "TEXT", name = "option_c")
    private String optionC;

    @Column(columnDefinition = "TEXT", name = "option_d")
    private String optionD;

    @NotBlank
    @Column(columnDefinition = "TEXT", name = "correct_answer")
    private String correctAnswer;

}
