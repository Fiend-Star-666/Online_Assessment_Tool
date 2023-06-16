package com.training.java.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "exams")
@Data
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account user;

    private int level;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<Answer> answers;

    private boolean submitted;

}
