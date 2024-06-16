package com.wzzy.virtualmovies.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private String jdbcURL = "jdbc:mysql://localhost:3306/yourDatabaseName";
    private String jdbcUsername = "root";
    private String jdbcPassword = "password";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // Handle the error properly
            System.out.println("Error connecting to the database: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}