package com.quizz.quizzspring.infrastructure.adapter.in.web.dto;

import com.quizz.quizzspring.domain.model.Question;

public record QuestionDto(
        Integer id,
        String questionTitle,
        String option1,
        String option2,
        String rightAnswer,
        String difficultylevel,
        String category) {

    public static QuestionDto from(Question q) {
        return new QuestionDto(q.id(), q.questionTitle(), q.option1(), q.option2(),
                q.rightAnswer(), q.difficultylevel(), q.category());
    }

    public Question toDomain() {
        return new Question(id, questionTitle, option1, option2, rightAnswer, difficultylevel, category);
    }
}
