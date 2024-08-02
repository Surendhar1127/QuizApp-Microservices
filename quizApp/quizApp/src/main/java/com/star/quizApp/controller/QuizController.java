package com.star.quizApp.controller;


import com.star.quizApp.model.Question;
import com.star.quizApp.model.QuestionWrapper;
import com.star.quizApp.model.Response;
import com.star.quizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController     {
@Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numbQ,@RequestParam String title    ){
        return quizService.createQuiz(category,numbQ,title);
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
