package com.example.quizmanagement;

import javafx.scene.control.Button;

public class QuestionTable {
	private String question;
	private Button delete;

	public QuestionTable() {
		this.question = "";
	}

	public QuestionTable(String question, Button delete) {
		this.question = question;
		this.delete = delete;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Button getDelete() {
		return delete;
	}

	public void setDelete(Button delete) {
		this.delete = delete;
	}
}
