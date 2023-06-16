package com.training.java.services;

import com.training.java.dto.AnswerDTO;
import com.training.java.dto.ExamReportDTO;
import com.training.java.dto.QuestionDTO;
import com.training.java.entities.Account;
import com.training.java.entities.Answer;
import com.training.java.entities.Exam;
import com.training.java.entities.Question;
import com.training.java.repositories.AccountRepository;
import com.training.java.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private QuestionService questionService;

    public boolean startExam(Long userId, int level) {

        Optional<Account> user = Optional.ofNullable(accountRepository.findById(userId));

        if (user.isEmpty()) {
            return false;
        }

        Exam exam = new Exam();
        exam.setUser(user.get());
        exam.setLevel(level);
        exam.setSubmitted(false);
        exam.setAnswers(new ArrayList<>());

        examRepository.save(exam);

        return true;
    }

    public ExamReportDTO getExamReport(Long examId) {
        Optional<Exam> exam = examRepository.findById(examId);

        if (exam.isEmpty()) {
            return null;
        }

        List<Answer> answers = exam.get().getAnswers();
        int totalQuestions = answers.size();
        int correctAnswers = 0;

        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for (Answer answer : answers) {
            Question question = answer.getQuestion();
            String userAnswer = answer.getUserAnswer();
            boolean isCorrect = question.getCorrectAnswer().equalsIgnoreCase(userAnswer);

            if (isCorrect) {
                correctAnswers++;
            }

            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestion(question.getQuestion());
            questionDTO.setRealCorrectAnswer(question.getCorrectAnswer());
            questionDTOs.add(questionDTO);
        }

        ExamReportDTO examReportDTO = new ExamReportDTO();
        examReportDTO.setExamId(exam.get().getId());
        examReportDTO.setUserName(exam.get().getUser().getName());
        examReportDTO.setLevel(exam.get().getLevel());
        examReportDTO.setQuestions(questionDTOs);
        examReportDTO.setTotalQuestions(totalQuestions);
        examReportDTO.setCorrectAnswers(correctAnswers);

        return examReportDTO;
    }

    public boolean submitExam(Long examId, List<AnswerDTO> answerDTOs) {
        Optional<Exam> exam = examRepository.findById(examId);

        if (exam.isEmpty()) {
            return false;
        }

        List<Answer> answers = new ArrayList<>();
        for (AnswerDTO answerDTO : answerDTOs) {
            Optional<Question> question = questionService.findById(answerDTO.getQuestionId());

            if (question.isPresent()) {
                Answer answer = new Answer();
                answer.setExam(exam.get());
                answer.setQuestion(question.get());
                answer.setUserAnswer(answerDTO.getUserAnswer());
                answers.add(answer);
            }
        }

        exam.get().setAnswers(answers);
        exam.get().setSubmitted(true);
        examRepository.save(exam.get());

        return true;
    }
}
