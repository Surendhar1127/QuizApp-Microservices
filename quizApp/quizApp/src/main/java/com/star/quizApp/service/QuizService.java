package com.star.quizApp.service;

import com.star.quizApp.model.Question;
import com.star.quizApp.model.QuestionWrapper;
import com.star.quizApp.model.Quiz;
import com.star.quizApp.model.Response;
import com.star.quizApp.repository.QuizzDB;
import com.star.quizApp.repository.RepositoryDB;
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
    RepositoryDB repositoryDB;

    public ResponseEntity<String> createQuiz(String category, int numbQ, String title) {
        Quiz quiz=new Quiz();
        List<Question> questions=repositoryDB.findRandomQuestionByCategory(category,numbQ);
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDB.save(quiz);
        return new ResponseEntity<>("sucess", HttpStatus.OK);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz=quizDB.findById(id);
        System.out.println(quiz);
        List<Question> questionFromDb=quiz.get().getQuestions();
        for(Question q:questionFromDb){
            System.out.println(q);
        }
        List<QuestionWrapper> questionforusers=new ArrayList<>();
        for(Question q:questionFromDb){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestiontitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionforusers.add(qw);
        }

        return new ResponseEntity<>(questionforusers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        Quiz quiz=quizDB.findById(id).get();
        List<Question> questionsFromDb=quiz.getQuestions();
int right=0;
int i=0;
        for(Response response :responses){

            if(response.getResponse().equals(questionsFromDb.get(i).getRightanswer())) {
                right++;
            }
            i++;
        }

        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
