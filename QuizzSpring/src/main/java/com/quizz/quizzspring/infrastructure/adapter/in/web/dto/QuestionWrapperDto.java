package com.quizz.quizzspring.infrastructure.adapter.in.web.dto;

import com.quizz.quizzspring.domain.model.QuestionSummary;

public record QuestionWrapperDto(Integer id, String questionTitle, String option1, String option2) {

    public static QuestionWrapperDto from(QuestionSummary s) {
        return new QuestionWrapperDto(s.id(), s.questionTitle(), s.option1(), s.option2());
    }
}
