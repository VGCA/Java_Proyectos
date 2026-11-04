package com.quizz.quizzspring.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "question")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String rightAnswer;
    private String difficultylevel;
    private String category;

    protected QuestionEntity() {
        // required by JPA
    }

    public QuestionEntity(Integer id, String questionTitle, String option1, String option2,
                          String rightAnswer, String difficultylevel, String category) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.option1 = option1;
        this.option2 = option2;
        this.rightAnswer = rightAnswer;
        this.difficultylevel = difficultylevel;
        this.category = category;
    }

    public Integer getId() { return id; }
    public String getQuestionTitle() { return questionTitle; }
    public String getOption1() { return option1; }
    public String getOption2() { return option2; }
    public String getRightAnswer() { return rightAnswer; }
    public String getDifficultylevel() { return difficultylevel; }
    public String getCategory() { return category; }
}
