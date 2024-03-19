package com.quiz.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.app.dao.QuestionDAO;
import com.quiz.app.dao.QuizDAO;
import com.quiz.app.entity.Question;
import com.quiz.app.entity.QuestionWrapper;
import com.quiz.app.entity.Quiz;
import com.quiz.app.entity.QuizResponse;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizDAO quizDAO;

	@Autowired
	private QuestionDAO questionDAO;

	@Override
	public Quiz createNewQuiz(String title, String difficultyLevel, String category, int noOfQue) {
		List<Question> questions = this.questionDAO.findRandomQuestions(category, difficultyLevel, noOfQue);
		Quiz quiz = new Quiz();
		quiz.setQuizTitle(title);
		quiz.setQuestions(questions);
		quiz = this.quizDAO.save(quiz);
		return quiz;
	}

	@Override
	public List<QuestionWrapper> getQuizByID(long quizId) {
		Quiz quiz = this.quizDAO.findById(quizId).get();
		List<Question> questions = quiz.getQuestions();
		List<QuestionWrapper> que = new ArrayList<>();
		for (Question question : questions) {
			que.add(new QuestionWrapper(question.getQuestionId(), question.getQuestionTitle(), question.getOption1(),
					question.getOption2(), question.getOption3(), question.getOption4()));

		}
		System.out.println(quiz);

		return que;
	}

	@Override
	public int calcResult(List<QuizResponse> quizResponse) {
		int result=0;
		List<Long> quizResponseQuestionIDList=new ArrayList<>();
		for(QuizResponse res : quizResponse) {
			quizResponseQuestionIDList.add(res.getQuestionID());
		}
		
		List<Question> questionFromDB=  this.questionDAO.findAllById(quizResponseQuestionIDList);
		
		for(QuizResponse res : quizResponse) {
			for(Question que : questionFromDB ) {
				if(res.getQuestionID()==que.getQuestionId()) {
					if(res.getResponse().equals(que.getCorrectAnswer())) {
						result++;
					}
				}
				
			}
			
		}

		return result;
	}

}
