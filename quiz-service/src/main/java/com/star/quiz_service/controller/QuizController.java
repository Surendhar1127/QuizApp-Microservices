package com.star.quiz_service.controller;



import com.star.quiz_service.model.QuestionWrapper;
import com.star.quiz_service.model.QuizDTO;
import com.star.quiz_service.model.Response;
import com.star.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController     {
@Autowired
QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO  ){//DTO--Data Transfer object
        return quizService.createQuiz(quizDTO.getCategory(),quizDTO.getNumbQuestions(),quizDTO.getTitle());
    }
    @GetMapping("get/{id}")

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
        return quizService.getQuizQuestion(id);
    }

    @PostMapping("submit/{id}")

    public ResponseEntity<Integer> submitquiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);
    }
}
