package com.quizz.quizzspring.domain.model;

public record Question(
        Integer id,
        String questionTitle,
        String option1,
        String option2,
        String rightAnswer,
        String difficultylevel,
        String category) {
}
