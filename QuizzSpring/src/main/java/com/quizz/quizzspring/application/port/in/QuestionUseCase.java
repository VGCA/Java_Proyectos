package com.quizz.quizzspring.application.port.in;

import com.quizz.quizzspring.domain.model.Question;

import java.util.List;

public interface QuestionUseCase {

    List<Question> getAllQuestions();

    List<Question> getQuestionsByCategory(String category);

    Question addQuestion(Question question);
}
