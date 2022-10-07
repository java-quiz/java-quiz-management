package com.example.quizmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
	@FXML
	private TextField email, pass;
	@FXML
	private Label error;

	@FXML
	private Button cancel, login;

	@FXML
	private void onLogin() {
		try {
			if (email.getText().equals("") || pass.getText().equals("")) {
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
				ResultSet rs = st.executeQuery("select * from `users` where `email` = '" + email.getText() + "' and " +
								"`password` = " + "'" + hashedPass + "'");
				if (rs != null && rs.next()) {
					Credentials.setUsername(rs.getString("username"));
					Credentials.setEmail(rs.getString("email"));
					Stage primaryStage = (Stage) login.getScene().getWindow();
					Parent root = rs.getString("admin").equals("1") ? FXMLLoader.load(getClass().getResource("teacher-view.fxml")) :
									FXMLLoader.load(getClass().getResource("student-view.fxml"));
					root.getStylesheets().add(getClass().getResource("/com/example/quizmanagement/styles.css").toExternalForm());
					primaryStage.setTitle(rs.getString("admin").equals("1") ? "Online Quiz Management" : "Online Quiz");
					primaryStage.setScene(new Scene(root, 400, 600));
					primaryStage.show();
				} else {
					error.setText("Incorrect username or password");
				}
			}

		} catch (Exception e) {
			error.setText("Error connecting to database");
		}
	}


	@FXML
	private void setOnKeyPressed(KeyEvent k) throws Exception {
		if (k.getCode().equals(KeyCode.ENTER)) {
			onLogin();
		}
	}
}
