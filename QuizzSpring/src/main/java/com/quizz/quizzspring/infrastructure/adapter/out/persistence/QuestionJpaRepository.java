package com.quizz.quizzspring.infrastructure.adapter.out.persistence;

import com.quizz.quizzspring.infrastructure.adapter.out.persistence.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionJpaRepository extends JpaRepository<QuestionEntity, Integer> {

    List<QuestionEntity> findByCategory(String category);

    @Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numQ",
            nativeQuery = true)
    List<QuestionEntity> findRandomByCategory(@Param("category") String category, @Param("numQ") int numQ);
}
