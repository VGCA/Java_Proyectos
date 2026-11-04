package com.quizz.quizzspring.application.service;

import com.quizz.quizzspring.application.port.out.QuizRepositoryPort;
import com.quizz.quizzspring.domain.model.Quiz;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryQuizRepository implements QuizRepositoryPort {

    private final Map<Integer, Quiz> store = new HashMap<>();
    private int nextId = 1;

    @Override
    public Quiz save(Quiz quiz) {
        Quiz saved = new Quiz(quiz.id() != null ? quiz.id() : nextId++, quiz.title(), quiz.questions());
        store.put(saved.id(), saved);
        return saved;
    }

    @Override
    public Optional<Quiz> findById(Integer id) {
        return Optional.ofNullable(store.get(id));
    }
}
