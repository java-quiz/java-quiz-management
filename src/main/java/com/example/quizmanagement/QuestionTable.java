package com.example.quizmanagement;

public class QuestionTable {
	private String question;
	private String answer;

	public QuestionTable() {
		this.question = "";
		this.answer = "";
	}

	public QuestionTable(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
