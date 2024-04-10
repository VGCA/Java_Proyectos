package com.quizz.QuizzSpring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quizz.QuizzSpring.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
