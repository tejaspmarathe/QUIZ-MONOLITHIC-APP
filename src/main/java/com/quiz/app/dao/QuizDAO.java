package com.quiz.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.app.entity.Quiz;

public interface QuizDAO extends JpaRepository<Quiz, Long> {

}
