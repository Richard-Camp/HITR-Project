package com.example.addressbook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteClubConnection {
    private static Connection instance = null;

    private SQLiteClubConnection() {
        String url = "jdbc:sqlite:clubs.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            new com.example.addressbook.model.SQLiteClubConnection();
        }
        return instance;
    }
}