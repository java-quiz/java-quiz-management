package com.example.quizmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	@FXML
	private Button addButton;
	@FXML
	private VBox tableCon;
	//	private TableView tableContainer;

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
		Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
		root.getStylesheets().add(getClass().getResource("/com/example/quizmanagement/styles.css").toExternalForm());
		primaryStage.setTitle("Test");
		primaryStage.setScene(new Scene(root, 400, 600));
		primaryStage.show();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		TableView table = new TableView<QuestionTable>();
		TableColumn qsCol = new TableColumn<QuestionTable, String>("Question");
		TableColumn dlCol = new TableColumn<QuestionTable, String>("Action");
		qsCol.setCellValueFactory(new PropertyValueFactory<QuestionTable, String>("Question"));
		dlCol.setCellValueFactory(new PropertyValueFactory<QuestionTable, String>("Delete"));
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getColumns().clear();
		table.getColumns().add(qsCol);
		table.getColumns().add(dlCol);
		tableCon.getChildren().add(table);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from quiz_list");
			//			dataTAble = FXCollections.observableArrayList();
			while (rs.next()) {
				//				tableContainer.setItems(rs.getString("Questinon"));
				table.getItems().add(new QuestionTable(rs.getString("Question"), new Button("Delete")));
			}
		} catch (Exception e) {
			//			report.setText("Error loading data from database");
		}
	}
}
