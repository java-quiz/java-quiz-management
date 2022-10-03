package com.example.quizmanagement;

import javafx.beans.property.SimpleStringProperty;

public class ResultTable {
	public SimpleStringProperty name;
	public SimpleStringProperty date;
	public SimpleStringProperty mark;

	public ResultTable() {
		this.name = new SimpleStringProperty("");
		this.date = new SimpleStringProperty("");
		this.mark = new SimpleStringProperty("");
	}

	public ResultTable(String name, String date, String mark) {
		this.name = new SimpleStringProperty(name);
		this.date = new SimpleStringProperty(date);
		this.mark = new SimpleStringProperty(mark);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public String getDate() {
		return date.get();
	}

	public void setDate(String date) {
		this.date = new SimpleStringProperty(date);
	}

	public String getMark() {
		return mark.get();
	}

	public void setMark(String mark) {
		this.mark = new SimpleStringProperty(mark);
	}
}
