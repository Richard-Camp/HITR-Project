package com.example.addressbook.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteJobConnection {
    private static Connection instance = null;

    private SQLiteJobConnection() {
        String url = "jdbc:sqlite:jobs.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            new SQLiteJobConnection();
        }
        return instance;
    }
}