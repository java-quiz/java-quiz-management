package com.example.quizmanagement;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class QuizController implements Initializable {
	
	ResultSet rs;
	float qsSize;
	String ans;
	float result = 0;
	@FXML
	private VBox quizBox;
	@FXML
	private Button goBack;
	@FXML
	private Label question;
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "");
			Statement st = con.createStatement();
			rs = st.executeQuery("Select * from quiz_list");
			if (rs != null) {
				rs.last();
				qsSize = rs.getRow();
				rs.beforeFirst();
				System.out.println(qsSize);
			}
			if (rs.next()) {
				updateQuestion();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void updateQuestion() throws Exception {
		quizBox.getChildren().clear();
		quizBox.getChildren().add(new Label(rs.getString("Question")));
		HBox opBox = new HBox();
		RadioButton o1 = new RadioButton(rs.getString("option1"));
		RadioButton o2 = new RadioButton(rs.getString("option2"));
		RadioButton o3 = new RadioButton(rs.getString("option3"));
		RadioButton o4 = new RadioButton(rs.getString("option4"));
		ToggleGroup optionGroup = new ToggleGroup();
		optionGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
				RadioButton rb = (RadioButton) optionGroup.getSelectedToggle();
				ans = rb.getText();
			}
		});
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
			
		}
	}
}
