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

public class StudentReportController implements Initializable {
	@FXML
	private Button exitButton;
	@FXML
	private VBox tableCon;

	@FXML
	private void onExit() throws Exception {
		Stage primaryStage = (Stage) exitButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("student-view.fxml"));
		root.getStylesheets().add(getClass().getResource("/com/example/quizmanagement/styles.css").toExternalForm());
		primaryStage.setTitle("Test");
		primaryStage.setScene(new Scene(root, 400, 600));
		primaryStage.show();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		TableView table = new TableView<ResultTable>();
		TableColumn nmCol = new TableColumn<ResultTable, String>("Name");
		TableColumn dtCol = new TableColumn<ResultTable, String>("Date");
		TableColumn mkCol = new TableColumn<ResultTable, String>("Mark");
		nmCol.setCellValueFactory(new PropertyValueFactory<ResultTable, String>("Name"));
		dtCol.setCellValueFactory(new PropertyValueFactory<ResultTable, String>("Date"));
		mkCol.setCellValueFactory(new PropertyValueFactory<ResultTable, String>("Mark"));
		mkCol.setStyle("-fx-alignment:CENTER");
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getColumns().clear();
		table.getColumns().add(nmCol);
		table.getColumns().add(dtCol);
		table.getColumns().add(mkCol);
		tableCon.getChildren().add(table);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from `result` where result.name = '" + Credentials.getUsername() + "'");
			while (rs.next()) {
				table.getItems().add(new ResultTable(rs.getString("name"), rs.getString("date"), rs.getString("mark")));
			}
		} catch (Exception e) {
		}
	}
}
