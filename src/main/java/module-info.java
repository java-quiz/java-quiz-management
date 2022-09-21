module com.example.quizmanagement {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;
	requires java.sql;
	
	requires org.controlsfx.controls;
	requires com.dlsc.formsfx;
	requires validatorfx;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.bootstrapfx.core;
	requires eu.hansolo.tilesfx;
	requires com.almasb.fxgl.all;
	
	opens com.example.quizmanagement to javafx.fxml;
	exports com.example.quizmanagement;
}