package com.star.question_service.repository;


import com.star.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RepositoryDB extends JpaRepository<Question,Integer>{//Tablename,id type

public List<Question> findByCategory(String category);

   public List<Question> findByDifficultylevel(String category);


@Query(value = "SELECT q. id FROM QUESTION q WHERE q.CATEGORY=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
   public List<Integer> findRandomQuestionByCategory(String category, Integer numQ);


}
