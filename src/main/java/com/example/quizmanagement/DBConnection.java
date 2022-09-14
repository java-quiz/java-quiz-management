package com.example.quizmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from quiz_list");
			while (rs.next()) {
				System.out.println("Question: " + rs.getString("Question"));
				System.out.println("Correct: " + rs.getString("correct"));
			}
			System.out.println("Connected to database");
			con.close();
		} catch (Exception e) {System.out.println(e);}
	}
}