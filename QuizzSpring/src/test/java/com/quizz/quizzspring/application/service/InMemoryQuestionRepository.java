package com.quizz.quizzspring.application.service;

import com.quizz.quizzspring.application.port.out.QuestionRepositoryPort;
import com.quizz.quizzspring.domain.model.Question;

import java.util.ArrayList;
import java.util.List;

/** Hand-written fake of the output port: no Mockito, no bytecode generation. */
public class InMemoryQuestionRepository implements QuestionRepositoryPort {

    private final List<Question> store = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<Question> findAll() {
        return List.copyOf(store);
    }

    @Override
    public List<Question> findByCategory(String category) {
        return store.stream().filter(q -> category.equals(q.category())).toList();
    }

    @Override
    public List<Question> findRandomByCategory(String category, int limit) {
        return findByCategory(category).stream().limit(limit).toList();
    }

    @Override
    public Question save(Question q) {
        Question saved = new Question(q.id() != null ? q.id() : nextId++, q.questionTitle(), q.option1(),
                q.option2(), q.rightAnswer(), q.difficultylevel(), q.category());
        store.add(saved);
        return saved;
    }
}
