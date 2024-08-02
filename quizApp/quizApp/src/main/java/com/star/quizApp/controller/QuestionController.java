package com.star.quizApp.controller;


import com.star.quizApp.model.Question;
import com.star.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
     QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionBycategory(@PathVariable String category){
        return questionService.getQuestionBycategory(category);
    }

    @GetMapping("difficultylevel/{difficultylevel}")
    public ResponseEntity<List<Question>> getQuestionByDifficultylevel(@PathVariable String difficultylevel){
        return questionService.getQuestionByDifficultylevel(difficultylevel);
    }


    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete")
    public String deleteQuestion(@RequestBody Question question){
        return questionService.deleteQuestion(question);
    }
}
