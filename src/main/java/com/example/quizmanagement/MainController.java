package com.example.quizmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {
	@FXML
	private Button addButton;

	@FXML
	private Button testButton;

	@FXML
	private void onClickAddButton() throws Exception {
		Stage primaryStage = (Stage) addButton.getScene().getWindow();
		Parent root;
		if (Credentials.isLoggedIn()) {
			root = FXMLLoader.load(getClass().getResource("manage-view.fxml"));
			primaryStage.setTitle("Add question");
		} else {
			root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
			primaryStage.setTitle("Login");
		}
		root.getStylesheets().add(getClass().getResource("/com/example/quizmanagement/styles.css").toExternalForm());
		primaryStage.setScene(new Scene(root, 400, 600));
		primaryStage.show();
	}

	@FXML
	private void onClickTestButton() throws Exception {
		Stage primaryStage = (Stage) testButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("quiz-view.fxml"));
		root.getStylesheets().add(getClass().getResource("/com/example/quizmanagement/styles.css").toExternalForm());
		primaryStage.setTitle("Test");
		primaryStage.setScene(new Scene(root, 400, 600));
		primaryStage.show();
	}

}