package com.example.quizmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddQuestionController implements Initializable {
	@FXML
	private Button goBack;
	@FXML
	private Label error, username;
	@FXML
	private TextArea question;
	@FXML
	private VBox container;
	@FXML
	private TextField op1, op2, op3, op4, cro;

	@FXML
	private void onClickGoBack() throws Exception {
		Stage primaryStage = (Stage) goBack.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
		root.getStylesheets().add(getClass().getResource("/com/example/quizmanagement/styles.css").toExternalForm());
		primaryStage.setTitle("Online Java Quiz Management System");
		primaryStage.setScene(new Scene(root, 400, 600));
		primaryStage.show();
	}

	@FXML
	private void onClickSubmit() throws Exception {
		if (op1.getText().equals("") || op2.getText().equals("") || op3.getText().equals("") || op4.getText().equals("") || cro.getText().equals("") || question.getText().equals("")) {
			error.setText("Please fill all the fields");
		} else if (!cro.getText().equals(op1.getText()) && !cro.getText().equals(op2.getText()) && !cro.getText().equals(op3.getText()) && !cro.getText().equals(op4.getText())) {
			error.setText("Correct ans doesn't match any of the above options");
			System.out.println(op1.getText() + op2.getText() + op3.getText() + op4.getText() + cro.getText());
		} else {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "");
			Statement st = con.createStatement();
			if (st.executeUpdate("INSERT INTO `quiz_list` (`Question`, `option1`, `option2`, `option3`, `option4`, `correct`) VALUES ('" + question.getText() + "', '" + op1.getText() + "', '" + op2.getText() + "', '" + op3.getText() + "', '" + op4.getText() + "', '" + cro.getText() + "');") == 1) {
				container.getChildren().clear();
				container.getChildren().add(new Label("Question added successfully"));
				container.getChildren().add(goBack);
				container.setAlignment(Pos.CENTER);
			}
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		username.setText("Welcome, " + Credentials.getUsername());
	}
}