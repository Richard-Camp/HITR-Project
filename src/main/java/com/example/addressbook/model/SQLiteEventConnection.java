package com.example.addressbook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteEventConnection {
    private static Connection instance = null;

    private SQLiteEventConnection() {
        String url = "jdbc:sqlite:events.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            new com.example.addressbook.model.SQLiteEventConnection();
        }
        return instance;
    }
}
