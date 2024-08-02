package com.star.quizApp.repository;

import com.star.quizApp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizzDB extends JpaRepository<Quiz,Integer> {
}
