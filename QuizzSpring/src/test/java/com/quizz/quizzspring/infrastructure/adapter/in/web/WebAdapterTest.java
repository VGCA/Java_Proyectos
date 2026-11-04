package com.quizz.quizzspring.infrastructure.adapter.in.web;

import com.quizz.quizzspring.application.service.InMemoryQuestionRepository;
import com.quizz.quizzspring.application.service.InMemoryQuizRepository;
import com.quizz.quizzspring.application.service.QuestionService;
import com.quizz.quizzspring.application.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** Driving-adapter test: real controllers + real services + in-memory fakes, no Spring context needed. */
class WebAdapterTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        InMemoryQuestionRepository questions = new InMemoryQuestionRepository();
        InMemoryQuizRepository quizzes = new InMemoryQuizRepository();
        mockMvc = MockMvcBuilders
                .standaloneSetup(
                        new QuestionController(new QuestionService(questions)),
                        new QuizController(new QuizService(quizzes, questions)))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void addQuestionReturnsCreated() throws Exception {
        mockMvc.perform(post("/question/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"questionTitle":"t","option1":"a","option2":"b",
                                 "rightAnswer":"a","difficultylevel":"easy","category":"java"}"""))
                .andExpect(status().isCreated())
                .andExpect(content().string("success"));

        mockMvc.perform(get("/question/allQuestions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].questionTitle").value("t"));
    }

    @Test
    void unknownQuizReturnsNotFound() throws Exception {
        mockMvc.perform(get("/quiz/get/42")).andExpect(status().isNotFound());
    }
}
