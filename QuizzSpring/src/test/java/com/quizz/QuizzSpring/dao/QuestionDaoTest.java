package com.quizz.QuizzSpring.dao;

import com.quizz.QuizzSpring.model.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionDaoTest {

    @Mock
    private QuestionDao questionDao;
    private List<Question> listQuestion;

    @BeforeEach
    void init() {
        questionDao = mock(QuestionDao.class);
        this.listQuestion = questionDao.findAll();
    }

    @Test
    void findNoneFieldsTest() {
        Assertions.assertEquals(0, questionDao.findAll().size());
    }

    @Test
    void findOneFieldTest() {
        Question question = new Question(1, "questionTitle", "option1",
                "option2", "option3", "option4", "rightAnswer",
                "difficultylevel", "category");
        when(questionDao.save(question)).thenReturn(question);
        questionDao.save(question);
        listQuestion = questionDao.findAll();
        Assertions.assertEquals(1, listQuestion.size());
    }
}
