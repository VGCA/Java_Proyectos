package com.quizz.quizzspring.application.port.out;

import com.quizz.quizzspring.domain.model.Question;

import java.util.List;

public interface QuestionRepositoryPort {

    List<Question> findAll();

    List<Question> findByCategory(String category);

    List<Question> findRandomByCategory(String category, int limit);

    Question save(Question question);
}
