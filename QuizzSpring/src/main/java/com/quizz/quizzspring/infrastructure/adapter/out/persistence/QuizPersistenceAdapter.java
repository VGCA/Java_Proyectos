package com.quizz.quizzspring.infrastructure.adapter.out.persistence;

import com.quizz.quizzspring.application.port.out.QuizRepositoryPort;
import com.quizz.quizzspring.domain.model.Quiz;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
class QuizPersistenceAdapter implements QuizRepositoryPort {

    private final QuizJpaRepository repository;

    QuizPersistenceAdapter(QuizJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Quiz save(Quiz quiz) {
        return PersistenceMapper.toDomain(repository.save(PersistenceMapper.toEntity(quiz)));
    }

    /** Transactional so the lazy ManyToMany is loaded before mapping (open-in-view is disabled). */
    @Override
    @Transactional(readOnly = true)
    public Optional<Quiz> findById(Integer id) {
        return repository.findById(id).map(PersistenceMapper::toDomain);
    }
}
