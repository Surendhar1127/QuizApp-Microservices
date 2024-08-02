package com.star.question_service.service;


import com.star.question_service.model.Question;
import com.star.question_service.model.QuestionWrapper;
import com.star.question_service.model.Response;
import com.star.question_service.repository.RepositoryDB;
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

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer numQ) {

        List<Integer> questionsId=repositoryDB.findRandomQuestionByCategory(category,numQ);

        return new ResponseEntity<>(questionsId,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionId) {

        List<QuestionWrapper> questionWrappers=new ArrayList<>();
        List<Question> questions=new ArrayList<>();

        for(Integer q:questionId){
            questions.add(repositoryDB.findById(q).get());
        }

        for(Question question:questions){
            QuestionWrapper qw=new QuestionWrapper(question.getId(),question.getQuestiontitle(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
            questionWrappers.add(qw);
        }

        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);

    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int right=0;

        for(Response response :responses){
Question question=repositoryDB.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightanswer())) {
                right++;
            }

        }

        return new ResponseEntity<>(right,HttpStatus.OK);

    }
}

