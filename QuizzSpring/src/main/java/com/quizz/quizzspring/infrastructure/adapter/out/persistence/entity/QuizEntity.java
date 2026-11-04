package com.quizz.quizzspring.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz")
public class QuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ManyToMany
    @JoinTable(name = "quiz_question",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "questions_id"))
    private List<QuestionEntity> questions = new ArrayList<>();

    protected QuizEntity() {
        // required by JPA
    }

    public QuizEntity(Integer id, String title, List<QuestionEntity> questions) {
        this.id = id;
        this.title = title;
        this.questions = new ArrayList<>(questions);
    }

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public List<QuestionEntity> getQuestions() { return questions; }
}
