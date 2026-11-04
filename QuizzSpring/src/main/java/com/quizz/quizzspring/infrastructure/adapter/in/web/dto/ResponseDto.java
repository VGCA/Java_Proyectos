package com.quizz.quizzspring.infrastructure.adapter.in.web.dto;

import com.quizz.quizzspring.domain.model.Answer;

public record ResponseDto(Integer id, String responseMessage) {

    public Answer toDomain() {
        return new Answer(id, responseMessage);
    }
}
