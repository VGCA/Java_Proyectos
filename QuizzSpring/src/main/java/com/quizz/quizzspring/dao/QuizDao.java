package com.quizz.quizzspring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quizz.quizzspring.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
