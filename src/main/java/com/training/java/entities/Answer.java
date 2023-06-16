package com.training.java.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_answers")
@Data
public class Answer {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "user_answer")
    private String userAnswer;

}
