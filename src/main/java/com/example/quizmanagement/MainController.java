package com.example.quizmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainController {
	@FXML
	private Button cancleButton;
	@FXML
	private Label loginMessageLabel;
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordField;

	public void loginButtonOnAction(ActionEvent e) {

		if (usernameTextField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {
			// loginMessageLabel.setText("You try to Login..!");
			validateLogin();
		} else {
			loginMessageLabel.setText("Please Enter Username & Password..");
		}
	}

	public void cancleButtonOnAction(ActionEvent e) {
		Stage stage = (Stage) cancleButton.getScene().getWindow();
		stage.close();
	}

	public void validateLogin() {
		DBConnection connectNow = new DBConnection();
		Connection conn = connectNow.getConnection();

		String verifyLogin = "SELECT count(1) FROM userAccounts WHERE Username = '" + usernameTextField.getText()
				+ "' AND Password = '" + passwordField.getText() + "'";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(verifyLogin);

			while (rs.next()) {

				if (rs.getInt(1) == 1) {
					loginMessageLabel.setText("Wellcome!");
				} else {
					loginMessageLabel.setText("Invalid Login. Please try again..");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}