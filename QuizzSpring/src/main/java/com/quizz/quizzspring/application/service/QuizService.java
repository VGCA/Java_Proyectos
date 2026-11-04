package com.quizz.quizzspring.application.service;

import com.quizz.quizzspring.application.port.in.QuizUseCase;
import com.quizz.quizzspring.application.port.out.QuestionRepositoryPort;
import com.quizz.quizzspring.application.port.out.QuizRepositoryPort;
import com.quizz.quizzspring.domain.exception.QuizNotFoundException;
import com.quizz.quizzspring.domain.model.Answer;
import com.quizz.quizzspring.domain.model.Question;
import com.quizz.quizzspring.domain.model.QuestionSummary;
import com.quizz.quizzspring.domain.model.Quiz;

import java.util.List;
import java.util.Objects;

public class QuizService implements QuizUseCase {

    private final QuizRepositoryPort quizRepository;
    private final QuestionRepositoryPort questionRepository;

    public QuizService(QuizRepositoryPort quizRepository, QuestionRepositoryPort questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Quiz createQuiz(String category, int numQuestions, String title) {
        List<Question> questions = questionRepository.findRandomByCategory(category, numQuestions);
        return quizRepository.save(new Quiz(null, title, questions));
    }

    @Override
    public List<QuestionSummary> getQuizQuestions(Integer quizId) {
        Quiz quiz = findQuiz(quizId);
        return quiz.questions().stream().map(QuestionSummary::from).toList();
    }

    @Override
    public int calculateResult(Integer quizId, List<Answer> answers) {
        List<Question> questions = findQuiz(quizId).questions();
        int right = 0;
        for (int i = 0; i < answers.size() && i < questions.size(); i++) {
            if (Objects.equals(answers.get(i).responseMessage(), questions.get(i).rightAnswer())) {
                right++;
            }
        }
        return right;
    }

    private Quiz findQuiz(Integer quizId) {
        return quizRepository.findById(quizId).orElseThrow(() -> new QuizNotFoundException(quizId));
    }
}
