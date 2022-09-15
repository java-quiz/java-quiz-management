package com.example.quizmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("Select * from quiz_list");
		for (int i = 0; rs.next(); i++) {
			quizBox.getChildren().add(new Label(i + 1 + ". " + rs.getString("Question")));
			HBox opBox = new HBox();
			RadioButton o1 = new RadioButton(rs.getString("option1"));
			RadioButton o2 = new RadioButton(rs.getString("option2"));
			RadioButton o3 = new RadioButton(rs.getString("option3"));
			RadioButton o4 = new RadioButton(rs.getString("option4"));
			ToggleGroup optionGroup = new ToggleGroup();
			o1.setToggleGroup(optionGroup);
			o2.setToggleGroup(optionGroup);
			o3.setToggleGroup(optionGroup);
			o4.setToggleGroup(optionGroup);
			opBox.getChildren().add(o1);
			opBox.getChildren().add(o2);
			opBox.getChildren().add(o3);
			opBox.getChildren().add(o4);
			quizBox.getChildren().add(opBox);
			
		}
		con.close();
		st.close();
		rs.close();
	}
}
