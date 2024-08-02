package com.star.quizApp.repository;

import com.star.quizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RepositoryDB extends JpaRepository<Question ,Integer>{//Tablename,id type

public List<Question> findByCategory(String category);

   public List<Question> findByDifficultylevel(String category);


@Query(value = "SELECT * FROM QUESTION q WHERE q.CATEGORY=:category ORDER BY RANDOM() LIMIT :numbQ",nativeQuery = true)
   public List<Question> findRandomQuestionByCategory(String category, int numbQ);


}
