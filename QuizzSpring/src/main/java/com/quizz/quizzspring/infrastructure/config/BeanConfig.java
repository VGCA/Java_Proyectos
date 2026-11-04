package com.quizz.quizzspring.infrastructure.config;

import com.quizz.quizzspring.application.port.in.QuestionUseCase;
import com.quizz.quizzspring.application.port.in.QuizUseCase;
import com.quizz.quizzspring.application.port.out.QuestionRepositoryPort;
import com.quizz.quizzspring.application.port.out.QuizRepositoryPort;
import com.quizz.quizzspring.application.service.QuestionService;
import com.quizz.quizzspring.application.service.QuizService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** The only place where the application core is wired to Spring. */
@Configuration
public class BeanConfig {

    @Bean
    QuestionUseCase questionUseCase(QuestionRepositoryPort questionRepository) {
        return new QuestionService(questionRepository);
    }

    @Bean
    QuizUseCase quizUseCase(QuizRepositoryPort quizRepository, QuestionRepositoryPort questionRepository) {
        return new QuizService(quizRepository, questionRepository);
    }
}
