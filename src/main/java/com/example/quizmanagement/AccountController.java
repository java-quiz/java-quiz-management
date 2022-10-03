package com.example.quizmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AccountController {
	@FXML
	private TextField username, email;
	@FXML
	private PasswordField pass;
	@FXML
	private Label error;

	@FXML
	private VBox container;
	@FXML
	private Button back;

	@FXML
	private void onCreate() {
		try {
			if (username.getText().equals("") || pass.getText().equals("") || email.getText().equals("")) {
				error.setText("Please fill all fields");
			} else {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] bytes = md.digest(pass.getText().getBytes());
				BigInteger no = new BigInteger(1, bytes);
				String hashedPass = no.toString(16);
				while (hashedPass.length() < 32) {
					hashedPass = "0" + hashedPass;
				}
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "");
				Statement st = con.createStatement();
				int rs = st.executeUpdate("INSERT INTO `users`(`username`, `email`, `password`, `admin`) VALUES ('" + username.getText() + "','" + email.getText() + "','" + hashedPass + "','0')");
				if (rs == 1) {
					container.getChildren().clear();
					container.getChildren().add(new Label("Question added successfully"));
					container.getChildren().add(back);
					container.setAlignment(Pos.CENTER);
				}
			}

		} catch (Exception e) {
			error.setText("Error connecting to database");
		}
	}

	@FXML
	private void onCancel() throws Exception {
		Stage primaryStage = (Stage) back.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("teacher-view.fxml"));
		root.getStylesheets().add(getClass().getResource("/com/example/quizmanagement/styles.css").toExternalForm());
		primaryStage.setTitle("Test");
		primaryStage.setScene(new Scene(root, 400, 600));
		primaryStage.show();
	}

	@FXML
	private void setOnKeyPressed(KeyEvent k) throws Exception {
		if (k.getCode().equals(KeyCode.ENTER)) {
			onCreate();
		}
	}
}
