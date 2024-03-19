package com.quiz.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.app.entity.Question;
import com.quiz.app.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@PostMapping("/addquestion")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		this.questionService.addQuestion(question);
		return new ResponseEntity<Question>(question, HttpStatus.CREATED);
	}

	@PostMapping("/addquestionlist")
	public ResponseEntity<List<Question>> addQuestionList(@RequestBody List<Question> questionList) {
		this.questionService.addQuestionList(questionList);
		return new ResponseEntity<List<Question>>(questionList, HttpStatus.CREATED);
	}

	@GetMapping("/getallquestion")
	public ResponseEntity<List<Question>> getAllQuestion() {
		List<Question> questionList = new ArrayList<>();
		questionList = this.questionService.getAllQuestions();
		return new ResponseEntity<List<Question>>(questionList, HttpStatus.OK);
	}

	@GetMapping("/getquestion/{questionid}")
	public ResponseEntity<Question> getQuestion(@PathVariable("questionid") Long questionid) {
		Question question = new Question();
		question = this.questionService.getQuestion(questionid);
		return new ResponseEntity<Question>(question, HttpStatus.OK);
	}

	@GetMapping("/getquestionbyCategoryAndDifficultyLevel")
	public ResponseEntity<List<Question>> getQuestionByCategoryAndDifficultyLevel(
			@RequestParam("category") String category, @RequestParam("difficultyLevel") String difficultyLevel) {
		List<Question> questionList = this.questionService.getQuestionByCategoryAndDifficultyLevel(category,
				difficultyLevel);
		return new ResponseEntity<List<Question>>(questionList, HttpStatus.OK);
	}

	@GetMapping("/getRandomQuestions")
	public ResponseEntity<List<Question>> findRandomQuestions(@RequestParam("category") String category,
			@RequestParam("difficultyLevel") String difficultyLevel, @RequestParam("noOfQuestions") int noOfQuestions) {
		List<Question> questionList = this.questionService.findRandomQuestions(category, difficultyLevel,
				noOfQuestions);
		return new ResponseEntity<List<Question>>(questionList, HttpStatus.OK);
	}

	@PutMapping("/updatequestion/{questionid}")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question,
			@PathVariable("questionid") Long questionid) {
		return this.questionService.updateQuestion(question, questionid);
	}

	@DeleteMapping("/delete/{questionid}")
	public ResponseEntity<Void> deleteQuestion(@PathVariable("questionid") Long questionid) {
		return this.questionService.deleteQuestion(questionid);
	}

}
