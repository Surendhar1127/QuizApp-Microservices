package com.star.quiz_service.service;



import com.star.quiz_service.feign.QuizInterface;
import com.star.quiz_service.model.QuestionWrapper;
import com.star.quiz_service.model.Quiz;
import com.star.quiz_service.model.Response;
import com.star.quiz_service.repository.QuizzDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizzDB quizDB;
@Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numbQ, String title) {

        List<Integer> questionIds=quizInterface.getQuestionForQuiz(category,numbQ).getBody();
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
        quizDB.save(quiz);
        return new ResponseEntity<>("sucess", HttpStatus.OK);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz=quizDB.findById(id);
      List<Integer> questionIds=quiz.get().getQuestionIds();
      System.out.println(questionIds);
ResponseEntity<List<QuestionWrapper>> questionsFromdb=quizInterface.getQuestionFromId(questionIds);

        return questionsFromdb;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

ResponseEntity<Integer> score=quizInterface.getScore(responses);

        return score;
    }
}
