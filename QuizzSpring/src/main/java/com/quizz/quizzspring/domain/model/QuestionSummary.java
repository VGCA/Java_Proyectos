package com.quizz.quizzspring.domain.model;

/** Question as shown to the player: it never exposes the right answer. */
public record QuestionSummary(Integer id, String questionTitle, String option1, String option2) {

    public static QuestionSummary from(Question question) {
        return new QuestionSummary(question.id(), question.questionTitle(), question.option1(), question.option2());
    }
}
