package com.example.quizmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuizController {
	
	@FXML
	private VBox quizBox;
	@FXML
	private Button goBack;
	@FXML
	private Label question;
	
	@FXML
	private void onClickGoBack() throws Exception {
		Stage stage = (Stage) goBack.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
		primaryStage.setTitle("Online Java Quiz Management System");
		primaryStage.setScene(new Scene(root, 400, 600));
		primaryStage.show();
	}
	
	@FXML
	private void onClickStart() throws Exception {
		List<Label> questionList = new ArrayList<>();
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("Select * from quiz_list");
		for (int i = 0; rs.next(); i++) {
			questionList.add(new Label(i + 1 + ". " + rs.getString("Question")));
		}
		quizBox.getChildren().addAll(questionList);
		con.close();
		st.close();
		rs.close();
	}
}
