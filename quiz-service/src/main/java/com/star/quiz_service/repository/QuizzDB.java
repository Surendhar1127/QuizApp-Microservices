package com.star.quiz_service.repository;


import com.star.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizzDB extends JpaRepository<Quiz,Integer> {
}
