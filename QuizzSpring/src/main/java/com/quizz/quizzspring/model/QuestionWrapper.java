package com.quizz.quizzspring.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QuestionWrapper {

    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;

    public QuestionWrapper(Integer id, String questionTitle, String option1, String option2) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.option1 = option1;
        this.option2 = option2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }
}
