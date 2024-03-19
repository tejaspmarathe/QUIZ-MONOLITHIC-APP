package com.quiz.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quiz.app.entity.Question;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Long> {
	
    List<Question> findByCategoryAndDifficultyLevel(String category, String difficultyLevel);
    
	@Query(value = "SELECT * FROM question WHERE category = ?1 AND difficulty_level = ?2 ORDER BY RAND() LIMIT ?3", nativeQuery = true)
    List<Question> findRandomQuestions(String category, String difficultyLevel, int numberOfQuestions);
    


}
