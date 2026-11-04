package com.quizz.quizzspring.domain.exception;

public class QuizNotFoundException extends RuntimeException {

    public QuizNotFoundException(Integer id) {
        super("Quiz not found: " + id);
    }
}
