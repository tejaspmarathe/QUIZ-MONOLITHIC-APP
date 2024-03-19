package com.quiz.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.app.dao.QuestionDAO;
import com.quiz.app.entity.Question;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO questiondao;

	@Override
	public void addQuestion(Question question) {
		this.questiondao.save(question);
	}

	@Override
	public void addQuestionList(List<Question> questionList) {
		this.questiondao.saveAll(questionList);
	}

	@Override
	public List<Question> getAllQuestions() {
		return this.questiondao.findAll();
	}

	@Override
	public Question getQuestion(Long questionID) {
		return this.questiondao.findById(questionID).get();
	}

	@Override
	public ResponseEntity<Question> updateQuestion(Question question, Long questionId) {
		Question questionFromDB = new Question();
		ResponseEntity<Question> responseEntity = null;
		Optional<Question> que = this.questiondao.findById(questionId);
		if (que.isPresent()) {
			questionFromDB = que.get();
			question.setQuestionId(questionFromDB.getQuestionId());
			this.questiondao.save(question);
			responseEntity = new ResponseEntity<Question>(questionFromDB, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<Question>(questionFromDB, HttpStatus.NOT_FOUND);
		}
		return responseEntity;

	}

	@Override
	public ResponseEntity<Void> deleteQuestion(Long questionID) {
		Optional<Question> que = this.questiondao.findById(questionID);
		if (que.isPresent()) {
			this.questiondao.deleteById(questionID);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@Override
	public List<Question> getQuestionByCategoryAndDifficultyLevel(String category, String difficultyLevel) {
		return this.questiondao.findByCategoryAndDifficultyLevel(category, difficultyLevel);
	}

	@Override
	public List<Question> findRandomQuestions(String category, String difficultyLevel, int noOfQuestions) {
		return this.questiondao.findRandomQuestions(category, difficultyLevel, noOfQuestions);
	}

}
