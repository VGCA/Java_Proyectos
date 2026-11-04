package com.quizz.quizzspring.application.service;

import com.quizz.quizzspring.application.port.in.QuestionUseCase;
import com.quizz.quizzspring.application.port.out.QuestionRepositoryPort;
import com.quizz.quizzspring.domain.model.Question;

import java.util.List;

/** Plain Java: no Spring, no JPA. It is registered as a bean in infrastructure.config.BeanConfig. */
public class QuestionService implements QuestionUseCase {

    private final QuestionRepositoryPort questionRepository;

    public QuestionService(QuestionRepositoryPort questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }
}
