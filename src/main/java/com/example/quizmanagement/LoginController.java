package com.example.quizmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
	@FXML
	private TextField username, pass;
	@FXML
	private Label error;

	@FXML
	private Button cancel, login;

	@FXML
	private void onLogin() throws Exception {
		if (username.getText().equals("") || pass.getText().equals("")) {
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
			ResultSet rs = st.executeQuery("select * from `users` where `username` = '" + username.getText() + "' and " +
							"`password` = " + "'" + hashedPass + "'");
			if (rs != null && rs.next()) {
				Credentials.setLoggedIn(true);
				Stage primaryStage = (Stage) cancel.getScene().getWindow();
				Parent root = FXMLLoader.load(getClass().getResource("add-view.fxml"));
				root.getStylesheets().add(getClass().getResource("/com/example/quizmanagement/styles.css").toExternalForm());
				primaryStage.setTitle("Online Java Quiz Management System");
				primaryStage.setScene(new Scene(root, 400, 600));
				primaryStage.show();
			} else {
				error.setText("Incorrect username or password");
			}
		}
	}

	@FXML
	private void onCancel() throws Exception {
		Stage primaryStage = (Stage) cancel.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
		root.getStylesheets().add(getClass().getResource("/com/example/quizmanagement/styles.css").toExternalForm());
		primaryStage.setTitle("Online Java Quiz Management System");
		primaryStage.setScene(new Scene(root, 400, 600));
		primaryStage.show();
	}
}
