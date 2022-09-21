package com.example.quizmanagement;

import java.sql.*;

public class DBConnection {
    public Connection dbLink;

    public Connection getConnection() {

        String dbName = "Quize-Management";
        String dbUser = "root";
        String dbPass = "";
        String url = "jdbc:mysql://localhost/" + dbName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbLink = DriverManager.getConnection(url,dbUser,dbPass);
        }catch (Exception e){
            e.printStackTrace();
        }

        return dbLink;

    }
}
