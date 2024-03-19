package com.quiz.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.app.entity.Question;

@Service
public interface QuestionService {

	void addQuestion(Question question);

	void addQuestionList(List<Question> questionList);

	List<Question> getAllQuestions();

	Question getQuestion(Long questionID);

	ResponseEntity<Void> deleteQuestion(Long questionID);

	ResponseEntity<Question> updateQuestion(Question question, Long questionId);

	List<Question> findRandomQuestions(String category, String difficultyLevel, int noOfQuestions);

	List<Question> getQuestionByCategoryAndDifficultyLevel(String category, String difficultyLevel);

}
