package com.example.addressbook.database;
import java.sql.*;

public class clubDB {
    private static Connection instance = null;

    private clubDB(){
        String url = "jdbc:sqlite:database.db";
        try{
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx){
            System.err.println(sqlEx);
        }
    }

    public static Connection getInstance(){
        if(instance == null){
            new clubDB();
        }
        return instance;
    }
}
