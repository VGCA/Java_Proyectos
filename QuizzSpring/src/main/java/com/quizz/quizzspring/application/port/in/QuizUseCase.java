package com.quizz.quizzspring.application.port.in;

import com.quizz.quizzspring.domain.model.Answer;
import com.quizz.quizzspring.domain.model.QuestionSummary;
import com.quizz.quizzspring.domain.model.Quiz;

import java.util.List;

public interface QuizUseCase {

    Quiz createQuiz(String category, int numQuestions, String title);

    List<QuestionSummary> getQuizQuestions(Integer quizId);

    int calculateResult(Integer quizId, List<Answer> answers);
}
