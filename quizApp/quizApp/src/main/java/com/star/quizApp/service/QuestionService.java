package com.star.quizApp.service;

import com.star.quizApp.model.Question;
import com.star.quizApp.repository.RepositoryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
@Autowired
RepositoryDB repositoryDB;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(repositoryDB.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionBycategory(String category) {
        try{
            return new ResponseEntity<>( repositoryDB.findByCategory(category),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        repositoryDB.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public String deleteQuestion(Question question) {
        repositoryDB.delete(question);
        return "success";
    }


    public ResponseEntity<List<Question>> getQuestionByDifficultylevel(String difficultylevel) {
        try{
            return new ResponseEntity<>( repositoryDB.findByCategory(difficultylevel),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
}

