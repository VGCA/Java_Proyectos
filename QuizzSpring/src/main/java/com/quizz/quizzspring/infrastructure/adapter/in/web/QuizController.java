package com.quizz.quizzspring.infrastructure.adapter.in.web;

import com.quizz.quizzspring.application.port.in.QuizUseCase;
import com.quizz.quizzspring.infrastructure.adapter.in.web.dto.QuestionWrapperDto;
import com.quizz.quizzspring.infrastructure.adapter.in.web.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private final QuizUseCase quizUseCase;

    public QuizController(QuizUseCase quizUseCase) {
        this.quizUseCase = quizUseCase;
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam int numQ,
                                             @RequestParam String title) {
        quizUseCase.createQuiz(category, numQ, title);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapperDto>> getQuizQuestions(@PathVariable Integer id) {
        return ResponseEntity.ok(quizUseCase.getQuizQuestions(id).stream().map(QuestionWrapperDto::from).toList());
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<ResponseDto> responses) {
        return ResponseEntity.ok(quizUseCase.calculateResult(id, responses.stream().map(ResponseDto::toDomain).toList()));
    }
}
