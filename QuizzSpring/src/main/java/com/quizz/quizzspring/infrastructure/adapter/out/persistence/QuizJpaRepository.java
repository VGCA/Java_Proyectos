package com.quizz.quizzspring.infrastructure.adapter.out.persistence;

import com.quizz.quizzspring.infrastructure.adapter.out.persistence.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizJpaRepository extends JpaRepository<QuizEntity, Integer> {
}
