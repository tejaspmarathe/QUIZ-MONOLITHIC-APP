package com.quiz.app.entity;

public class QuizResponse {
	private long questionID;
	private String response;

	public QuizResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuizResponse(long questionID, String response) {
		super();
		this.questionID = questionID;
		this.response = response;
	}

	public long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(long questionID) {
		this.questionID = questionID;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "QuizResponse [questionID=" + questionID + ", response=" + response + "]";
	}

}
