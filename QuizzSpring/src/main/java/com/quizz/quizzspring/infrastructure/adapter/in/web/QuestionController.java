package com.quizz.quizzspring.infrastructure.adapter.in.web;

import com.quizz.quizzspring.application.port.in.QuestionUseCase;
import com.quizz.quizzspring.infrastructure.adapter.in.web.dto.QuestionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    private final QuestionUseCase questionUseCase;

    public QuestionController(QuestionUseCase questionUseCase) {
        this.questionUseCase = questionUseCase;
    }

    @GetMapping("allQuestions")
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        return ResponseEntity.ok(questionUseCase.getAllQuestions().stream().map(QuestionDto::from).toList());
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(questionUseCase.getQuestionsByCategory(category).stream().map(QuestionDto::from).toList());
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody QuestionDto question) {
        questionUseCase.addQuestion(question.toDomain());
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }
}
