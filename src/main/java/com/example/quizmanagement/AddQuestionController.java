package com.example.quizmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AddQuestionController {
    @FXML
    private Button goBack;


    @FXML
    private Label error;
    @FXML
    private TextArea question;

    @FXML
    private VBox container;
    @FXML
    private TextField op1;
    @FXML
    private TextField op2;
    @FXML
    private TextField op3;
    @FXML
    private TextField op4;
    @FXML
    private TextField cro;
    @FXML
    private Label clickText;
    @FXML
    private Button submit;

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
    private void onClickSubmit() throws Exception {
        if (op1.getText().equals("") || op2.getText().equals("") || op3.getText().equals("") || op4.getText().equals("") || cro.getText().equals("") || question.getText().equals("")) {
            error.setText("Please fill all the fields");
        } else if (!cro.getText().equals(op1.getText()) || !cro.getText().equals(op2.getText()) || !cro.getText().equals(op3.getText()) || !cro.getText().equals(op4.getText())) {
            error.setText("Correct ans doesn't match any of the above options");
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
}