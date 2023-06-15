package com.training.java.repositories;

import com.training.java.entities.Question;
import com.training.java.entities.enums.CodingLanguageEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByLanguageAndLevelOfQuestion(CodingLanguageEnum language, Integer level);

    List<Question> findByLanguage(String language);

    List<Question> findByLevelOfQuestion(Integer level);
}

