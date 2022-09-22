package com.example.quizmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 400, 600);
		scene.getStylesheets().add(getClass().getResource("/com/example/quizmanagement/styles.css").toExternalForm());
		stage.setTitle("Online Java Quiz Management System");
		stage.setScene(scene);
		stage.show();
	}
}