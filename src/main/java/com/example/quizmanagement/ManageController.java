package com.example.quizmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ManageController implements Initializable {
	TableView table = new TableView<QuestionTable>();
	TableColumn qsCol = new TableColumn<QuestionTable, String>("Question");
	TableColumn crCol = new TableColumn<QuestionTable, String>("Answer");
	@FXML
	private Button addButton;
	@FXML
	private VBox tableCon;
	@FXML
	private Label username;

	@FXML
	private void onAdd() throws Exception {
		Stage primaryStage = (Stage) addButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("add-view.fxml"));
		root.getStylesheets().add(getClass().getResource("/com/example/quizmanagement/styles.css").toExternalForm());
		primaryStage.setTitle("Test");
		primaryStage.setScene(new Scene(root, 400, 600));
		primaryStage.show();
	}

	@FXML
	private void onExit() throws Exception {
		Stage primaryStage = (Stage) addButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("teacher-view.fxml"));
		root.getStylesheets().add(getClass().getResource("/com/example/quizmanagement/styles.css").toExternalForm());
		primaryStage.setTitle("Test");
		primaryStage.setScene(new Scene(root, 400, 600));
		primaryStage.show();
	}

	@FXML
	private void deleteQs() {
		//		Object qsPos = table.getSelectionModel().getSelectedCells().get(0);
		//		int row = qsPos.getRow();
		//		TableView item = table.getItems().get(row);
		//		ResultTable ques = table.getSelectionModel().getSelectedItem();
		//		System.out.println(ques);
		table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
		//										System.out.println(qs);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		username.setText("Welcome, " + Credentials.getUsername());
		qsCol.setCellValueFactory(new PropertyValueFactory<QuestionTable, String>("Question"));
		crCol.setCellValueFactory(new PropertyValueFactory<QuestionTable, String>("Answer"));
		crCol.setStyle("-fx-alignment:CENTER");
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getColumns().clear();
		table.getColumns().add(qsCol);
		table.getColumns().add(crCol);
		tableCon.getChildren().add(table);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from quiz_list");
			while (rs.next()) {
				Button del = new Button("Delete");
				table.getItems().add(new QuestionTable(rs.getString("Question"), rs.getString("correct")));
			}
		} catch (Exception e) {
		}
	}
}
