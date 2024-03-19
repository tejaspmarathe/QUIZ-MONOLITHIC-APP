package com.quiz.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quiz.app.entity.QuestionWrapper;
import com.quiz.app.entity.Quiz;
import com.quiz.app.entity.QuizResponse;

@Service
public interface QuizService {

	Quiz createNewQuiz(String title,String difficultyLevel,String category,int noOfQue);

	List<QuestionWrapper> getQuizByID(long quizId);

	int calcResult(List<QuizResponse> quizResponse);

}
