package com.quizz.quizzspring.domain.model;

import java.util.List;

public record Quiz(Integer id, String title, List<Question> questions) {

    public Quiz {
        questions = questions == null ? List.of() : List.copyOf(questions);
    }
}
