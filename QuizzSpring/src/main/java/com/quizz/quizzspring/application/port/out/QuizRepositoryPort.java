package com.quizz.quizzspring.application.port.out;

import com.quizz.quizzspring.domain.model.Quiz;

import java.util.Optional;

public interface QuizRepositoryPort {

    Quiz save(Quiz quiz);

    Optional<Quiz> findById(Integer id);
}
