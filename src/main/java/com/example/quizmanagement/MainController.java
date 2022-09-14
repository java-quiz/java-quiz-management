package com.example.quizmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController {
	@FXML
	private Label welcomeText;
	
	@FXML
	private Button addButton;
	@FXML
	private void onClickAddButton() throws Exception{
		Stage stage = (Stage) addButton.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("add-question-view.fxml"));
		primaryStage.setTitle("Add question");
		primaryStage.setScene(new Scene(root, 800, 500));
		primaryStage.show();
	}
	@FXML
	protected void onHelloButtonClick() {
		welcomeText.setText("Welcome to JavaFX Application!");
	}
	
}