package com.quizz.quizzspring.infrastructure.adapter.out.persistence;

import com.quizz.quizzspring.application.port.out.QuestionRepositoryPort;
import com.quizz.quizzspring.domain.model.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class QuestionPersistenceAdapter implements QuestionRepositoryPort {

    private final QuestionJpaRepository repository;

    QuestionPersistenceAdapter(QuestionJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Question> findAll() {
        return repository.findAll().stream().map(PersistenceMapper::toDomain).toList();
    }

    @Override
    public List<Question> findByCategory(String category) {
        return repository.findByCategory(category).stream().map(PersistenceMapper::toDomain).toList();
    }

    @Override
    public List<Question> findRandomByCategory(String category, int limit) {
        return repository.findRandomByCategory(category, limit).stream().map(PersistenceMapper::toDomain).toList();
    }

    @Override
    public Question save(Question question) {
        return PersistenceMapper.toDomain(repository.save(PersistenceMapper.toEntity(question)));
    }
}
