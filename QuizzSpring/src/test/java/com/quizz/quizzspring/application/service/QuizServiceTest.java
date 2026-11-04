package com.quizz.quizzspring.application.service;

import com.quizz.quizzspring.domain.exception.QuizNotFoundException;
import com.quizz.quizzspring.domain.model.Answer;
import com.quizz.quizzspring.domain.model.QuestionSummary;
import com.quizz.quizzspring.domain.model.Question;
import com.quizz.quizzspring.domain.model.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuizServiceTest {

    private QuizService service;

    @BeforeEach
    void setUp() {
        InMemoryQuestionRepository questions = new InMemoryQuestionRepository();
        questions.save(new Question(null, "q1", "a", "b", "a", "easy", "java"));
        questions.save(new Question(null, "q2", "c", "d", "d", "easy", "java"));
        service = new QuizService(new InMemoryQuizRepository(), questions);
    }

    @Test
    void createQuizPicksQuestionsOfTheCategory() {
        Quiz quiz = service.createQuiz("java", 2, "My quiz");

        assertEquals(1, quiz.id());
        assertEquals(2, quiz.questions().size());
    }

    @Test
    void quizQuestionsDoNotExposeTheRightAnswer() {
        Quiz quiz = service.createQuiz("java", 2, "My quiz");

        List<QuestionSummary> summaries = service.getQuizQuestions(quiz.id());

        assertEquals(2, summaries.size());
        assertEquals("q1", summaries.get(0).questionTitle());
    }

    @Test
    void calculateResultCountsRightAnswers() {
        Quiz quiz = service.createQuiz("java", 2, "My quiz");

        int result = service.calculateResult(quiz.id(), List.of(new Answer(1, "a"), new Answer(2, "wrong")));

        assertEquals(1, result);
    }

    @Test
    void unknownQuizThrowsNotFound() {
        assertThrows(QuizNotFoundException.class, () -> service.getQuizQuestions(99));
        assertThrows(QuizNotFoundException.class, () -> service.calculateResult(99, List.of()));
    }
}
