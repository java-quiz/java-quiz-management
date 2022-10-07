package com.example.quizmanagement;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class QuizController implements Initializable {

	ResultSet rs;
	Statement st;
	float qsSize, progress = 0;
	String ans;
	float result = 0;
	@FXML
	private VBox quizBox;
	@FXML
	private Button goBack, nextButton;
	@FXML
	private Label question, report;
	@FXML
	private HBox btnContainer;
	@FXML
	private GridPane opBox;
	@FXML
	private ProgressBar pb;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "");
			st = con.createStatement();
			rs = st.executeQuery("Select * from quiz_list");
			if (rs != null) {
				rs.last();
				qsSize = rs.getRow();
				rs.beforeFirst();
			}
			if (rs.next()) {
				updateQuestion();
			}
		} catch (Exception e) {
			report.setText("No question in found. Please try adding questions");
		}
	}

	private void updateQuestion() throws Exception {
		quizBox.getChildren().clear();
		question.setText(rs.getString("question"));
		quizBox.getChildren().add(question);
		opBox.getChildren().clear();
		RadioButton o1 = new RadioButton(rs.getString("option1"));
		o1.wrapTextProperty().set(true);
		RadioButton o2 = new RadioButton(rs.getString("option2"));
		o2.wrapTextProperty().set(true);
		RadioButton o3 = new RadioButton(rs.getString("option3"));
		o3.wrapTextProperty().set(true);
		RadioButton o4 = new RadioButton(rs.getString("option4"));
		o4.wrapTextProperty().set(true);
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
		opBox.add(o1, 0, 0);
		opBox.add(o2, 1, 0);
		opBox.add(o3, 0, 1);
		opBox.add(o4, 1, 1);
		quizBox.getChildren().add(opBox);

	}

	@FXML
	private void onClickGoBack() throws Exception {
		Stage primaryStage = (Stage) goBack.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("student-view.fxml"));
		root.getStylesheets().add(getClass().getResource("/com/example/quizmanagement/styles.css").toExternalForm());
		primaryStage.setTitle("Online Quiz");
		primaryStage.setScene(new Scene(root, 400, 600));
		primaryStage.show();
	}

	@FXML
	private void onClickStart() throws Exception {
		if (ans != null) {
			pb.setProgress(progress += (1 / qsSize));
			report.setText("");
			if (rs.getString("correct").equals(ans)) {
				result++;
			}
			if (rs.next()) {
				updateQuestion();
			} else {
				quizBox.getChildren().clear();
				btnContainer.getChildren().remove(nextButton);
				Button reportButton = new Button("Show report");
				reportButton.setId("purple-button");
				reportButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent actionEvent) {
						try {
							Stage primaryStage = (Stage) goBack.getScene().getWindow();
							Parent root = FXMLLoader.load(getClass().getResource("student-report-view.fxml"));
							root.getStylesheets().add(getClass().getResource("/com/example/quizmanagement/styles.css").toExternalForm());
							primaryStage.setTitle("Online Quiz");
							primaryStage.setScene(new Scene(root, 400, 600));
							primaryStage.show();
						} catch (Exception e) {
							System.out.println(e);
						}
					}
				});
				btnContainer.getChildren().add(reportButton);
				ObservableList<PieChart.Data> pieChartData =
								FXCollections.observableArrayList(
												new PieChart.Data("Correct " + String.valueOf(Math.round((result / qsSize) * 100) + "% (" + Math.round(result) +
																")"),
																((result / qsSize) * 100)),
												new PieChart.Data("Wrong " + String.valueOf(Math.round(((qsSize - result) / qsSize) * 100) +
																"% (" + Math.round(qsSize - result) + ")"),
																(((qsSize - result) / qsSize) * 100)));
				final PieChart chart = new PieChart(pieChartData);
				st.executeUpdate("INSERT INTO `result`(`name`, `email`, `date`, `mark`) VALUES ('" + Credentials.getUsername() + "','" + Credentials.getEmail() + "'," +
								"'" + new SimpleDateFormat("dd-MMM hh:mm aa").format(new Date()) + "','" + Math.round(result) + "/" + Math.round(qsSize) + "')");
				chart.setAnimated(true);
				chart.setLabelsVisible(false);
				chart.setTitle("Quiz result");
				quizBox.getChildren().add(chart);
			}
			ans = null;
		} else {
			report.setText("Please select an option");
		}
	}

}
