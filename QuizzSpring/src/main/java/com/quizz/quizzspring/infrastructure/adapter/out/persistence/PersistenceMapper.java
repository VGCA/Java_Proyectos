package com.quizz.quizzspring.infrastructure.adapter.out.persistence;

import com.quizz.quizzspring.domain.model.Question;
import com.quizz.quizzspring.domain.model.Quiz;
import com.quizz.quizzspring.infrastructure.adapter.out.persistence.entity.QuestionEntity;
import com.quizz.quizzspring.infrastructure.adapter.out.persistence.entity.QuizEntity;

final class PersistenceMapper {

    private PersistenceMapper() {
    }

    static Question toDomain(QuestionEntity e) {
        return new Question(e.getId(), e.getQuestionTitle(), e.getOption1(), e.getOption2(),
                e.getRightAnswer(), e.getDifficultylevel(), e.getCategory());
    }

    static QuestionEntity toEntity(Question q) {
        return new QuestionEntity(q.id(), q.questionTitle(), q.option1(), q.option2(),
                q.rightAnswer(), q.difficultylevel(), q.category());
    }

    static Quiz toDomain(QuizEntity e) {
        return new Quiz(e.getId(), e.getTitle(), e.getQuestions().stream().map(PersistenceMapper::toDomain).toList());
    }

    static QuizEntity toEntity(Quiz q) {
        return new QuizEntity(q.id(), q.title(), q.questions().stream().map(PersistenceMapper::toEntity).toList());
    }
}
