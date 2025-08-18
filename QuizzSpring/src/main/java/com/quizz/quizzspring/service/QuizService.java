package com.quizz.quizzspring.service;

import com.quizz.quizzspring.dao.QuestionDao;
import com.quizz.quizzspring.dao.QuizDao;
import com.quizz.quizzspring.model.Question;
import com.quizz.quizzspring.model.QuestionWrapper;
import com.quizz.quizzspring.model.Quiz;
import com.quizz.quizzspring.model.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuizService {

    private QuizDao quizDao;
    private QuestionDao questionDao;
    private List<Question> questionsFromDB;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        quiz.ifPresent(value -> questionsFromDB = value.getQuestions());
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> optionalQuiz = quizDao.findById(id);
        if (!optionalQuiz.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Quiz quiz = optionalQuiz.get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;

        for (int i = 0; i < responses.size() && i < questions.size(); i++) {
            Response response = responses.get(i);
            if (response.getResponseMessage().equals(questions.get(i).getRightAnswer())) {
                right++;
            }
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }

}
