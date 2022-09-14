package com.example.quizmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AddQuestionController {
	@FXML
	private Button goBack;
	
	@FXML
	private Label clickText;
	@FXML
	private Button submit;
	@FXML
	private void onClickGoBack() throws Exception{
		Stage stage = (Stage) goBack.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
		primaryStage.setTitle("Online Java Quiz Management System");
		primaryStage.setScene(new Scene(root, 400, 600));
		primaryStage.show();
	}
	@FXML
	private void onClickSubmit() {
		clickText.setText("Button clicked");
	}
}
