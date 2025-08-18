package com.quizz.quizzspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.quizz.quizzspring.model.QuestionWrapper;
import com.quizz.quizzspring.model.Response;
import com.quizz.quizzspring.service.QuizService;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    private final QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }


}
