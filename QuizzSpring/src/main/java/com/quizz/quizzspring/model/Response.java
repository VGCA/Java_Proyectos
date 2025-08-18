package com.quizz.quizzspring.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Response {
    private Integer id;
    private String responseMessage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
