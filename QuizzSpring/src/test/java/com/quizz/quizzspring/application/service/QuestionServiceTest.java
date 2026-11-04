package com.quizz.quizzspring.application.service;

import com.quizz.quizzspring.domain.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionServiceTest {

    private QuestionService service;

    @BeforeEach
    void setUp() {
        service = new QuestionService(new InMemoryQuestionRepository());
    }

    @Test
    void findNoQuestionsWhenRepositoryIsEmpty() {
        assertTrue(service.getAllQuestions().isEmpty());
    }

    @Test
    void addQuestionAssignsIdAndIsReturnedByAllQuestions() {
        Question saved = service.addQuestion(
                new Question(null, "title", "option1", "option2", "option1", "easy", "java"));

        assertEquals(1, saved.id());
        assertEquals(1, service.getAllQuestions().size());
    }

    @Test
    void filtersByCategory() {
        service.addQuestion(new Question(null, "t1", "a", "b", "a", "easy", "java"));
        service.addQuestion(new Question(null, "t2", "a", "b", "a", "easy", "python"));

        assertEquals(1, service.getQuestionsByCategory("java").size());
        assertEquals("t2", service.getQuestionsByCategory("python").get(0).questionTitle());
    }
}
