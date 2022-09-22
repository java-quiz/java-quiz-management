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
		Stage stage = (Stage) addButton.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		System.out.println(Credentials.isLoggedIn());
		if (Credentials.isLoggedIn()) {
			Parent root = FXMLLoader.load(getClass().getResource("add-view.fxml"));
			root.getStylesheets().add(getClass().getResource("/com/example/quizmanagement/styles.css").toExternalForm());
			primaryStage.setTitle("Add question");
			primaryStage.setScene(new Scene(root, 400, 600));
			primaryStage.show();
		} else {
			Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
			root.getStylesheets().add(getClass().getResource("/com/example/quizmanagement/styles.css").toExternalForm());
			primaryStage.setTitle("Login");
			primaryStage.setScene(new Scene(root, 400, 600));
			primaryStage.show();
		}
	}

	@FXML
	private void onClickTestButton() throws Exception {
		Stage stage = (Stage) testButton.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("quiz-view.fxml"));
		root.getStylesheets().add(getClass().getResource("/com/example/quizmanagement/styles.css").toExternalForm());
		primaryStage.setTitle("Test");
		primaryStage.setScene(new Scene(root, 400, 600));
		primaryStage.show();
	}

}