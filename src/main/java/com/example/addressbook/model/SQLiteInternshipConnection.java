package com.example.addressbook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteInternshipConnection {
    private static Connection instance = null;

    private SQLiteInternshipConnection() {
        String url = "jdbc:sqlite:internships.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            new com.example.addressbook.model.SQLiteInternshipConnection();
        }
        return instance;
    }
}

