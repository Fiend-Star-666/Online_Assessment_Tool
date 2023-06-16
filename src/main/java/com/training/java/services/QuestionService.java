package com.training.java.services;

import com.training.java.entities.Question;
import com.training.java.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Optional<Question> findById(Long id) {
        if(questionRepository.findById(id).isEmpty())
            throw new RuntimeException("Question not found");
        else
        return questionRepository.findById(id);

    }

    // Add other methods as per your requirement

}
