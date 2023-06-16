package com.training.java.csvHandlers;

import com.training.java.dto.QuestionDTO;
import com.training.java.entities.Question;
import com.training.java.entities.enums.CodingLanguageEnum;
import com.training.java.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class CsvToQuestionnaire {

    private static final String CSV_DIRECTORY_PATH = "src/main/resources/questionsInCsvFormat";

    private final QuestionRepository questionRepository;

    @Autowired
    public CsvToQuestionnaire(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void saveQuestionsToDb() throws IOException {
        Map<String, List<QuestionDTO>> questionnaires = readQuestionnaires();

        for (List<QuestionDTO> questions : questionnaires.values()) {
            for (QuestionDTO questionDto : questions) {
                Question question = convertToQuestionEntity(questionDto);
                questionRepository.save(question);
            }
        }
    }

    public List<QuestionDTO> getQuestionsByLanguageAndLevel(String language, Integer level) {
        CodingLanguageEnum languageEnum = CodingLanguageEnum.valueOf(language.toUpperCase());
        List<Question> questions = questionRepository.findByLanguageAndLevelOfQuestion(languageEnum, level);
        return convertToQuestionDtoList(questions);
    }

    public List<QuestionDTO> getQuestionsByLanguage(String language) {
        List<Question> questions = questionRepository.findByLanguage(language);
        return convertToQuestionDtoList(questions);
    }

    public List<QuestionDTO> getQuestionsByLevel(Integer level) {
        List<Question> questions = questionRepository.findByLevelOfQuestion(level);
        return convertToQuestionDtoList(questions);
    }

    public List<QuestionDTO> getRandomQuestions(int numberOfQuestions) {

        List<Question> questions = questionRepository.findAll();
        List<QuestionDTO> questionDTOS = convertToQuestionDtoList(questions);

        List<QuestionDTO> randomQuestions = new ArrayList<>();
        if (questionDTOS.size() <= numberOfQuestions) {
            randomQuestions.addAll(questionDTOS);
        } else {
            Random random = new Random();
            while (randomQuestions.size() < numberOfQuestions) {
                int randomIndex = random.nextInt(questionDTOS.size());
                QuestionDTO randomQuestion = questionDTOS.get(randomIndex);
                randomQuestions.add(randomQuestion);
                questionDTOS.remove(randomIndex);
            }
        }
        return randomQuestions;
    }

    private Map<String, List<QuestionDTO>> readQuestionnaires() throws IOException {
        File questionnaireDirectory = new File(CSV_DIRECTORY_PATH);
        Map<String, List<QuestionDTO>> questionnaires = new HashMap<>();

        File[] csvFiles = questionnaireDirectory.listFiles();

        if (csvFiles != null) {
            for (File csvFile : csvFiles) {
                String language = csvFile.getName().replace(".csv", "");
                List<QuestionDTO> questions = readQuestionsFromCsv(csvFile);
                questionnaires.put(language, questions);
            }
        }

        return questionnaires;
    }

    private List<QuestionDTO> readQuestionsFromCsv(File csvFile) throws IOException {
        List<QuestionDTO> questions = new ArrayList<>();

        try (Scanner scanner = new Scanner(csvFile)) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip the header line
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",", -1);

                if (data.length == 8) {
                    String language = data[0].trim();
                    Integer levelOfQuestion = Integer.parseInt(data[1].trim());
                    String question = data[2].trim();
                    String optionA = data[3].trim();
                    String optionB = data[4].trim();
                    String optionC = data[5].trim();
                    String optionD = data[6].trim();
                    String correctAnswer = data[7].trim();

                    QuestionDTO questionDto = new QuestionDTO(language, levelOfQuestion, question, optionA, optionB, optionC, optionD, correctAnswer);
                    questions.add(questionDto);
                }
            }
        }

        return questions;
    }

    private Question convertToQuestionEntity(QuestionDTO questionDto) {
        Question question = new Question();
        CodingLanguageEnum codingLanguageEnum = CodingLanguageEnum.valueOf(questionDto.getLanguage().toUpperCase());
        question.setLanguage(codingLanguageEnum);
        question.setLevelOfQuestion(questionDto.getLevelOfQuestion());
        question.setQuestion(questionDto.getQuestion());
        question.setOptionA(questionDto.getOptionA());
        question.setOptionB(questionDto.getOptionB());
        question.setOptionC(questionDto.getOptionC());
        question.setOptionD(questionDto.getOptionD());
        question.setCorrectAnswer(questionDto.getCorrectAnswer());
        return question;
    }

    private List<QuestionDTO> convertToQuestionDtoList(List<Question> questions) {
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            QuestionDTO questionDto = new QuestionDTO(
                    question.getLanguage().toString(),
                    question.getLevelOfQuestion(),
                    question.getQuestion(),
                    question.getOptionA(),
                    question.getOptionB(),
                    question.getOptionC(),
                    question.getOptionD(),
                    question.getCorrectAnswer()
            );
            questionDTOS.add(questionDto);
        }
        return questionDTOS;
    }
}
