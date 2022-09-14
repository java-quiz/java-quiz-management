package com.example.quizmanagement;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "");
			//here sonoo is database name, root is username and password
			System.out.println("Connected to database");
			con.close();
		} catch (Exception e) {System.out.println(e);}
	}
}