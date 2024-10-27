package com.example.addressbook.model.Events;

import com.example.addressbook.model.SQLiteEventConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteEventDAO {
    private Connection connection;

    public SqliteEventDAO() {
        connection = SQLiteEventConnection.getInstance();
        createTable();
        addClubs();
    }

    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS events ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "eventName VARCHAR NOT NULL,"
                    + "companyName VARCHAR NOT NULL,"
                    + "eventDate VARCHAR NOT NULL,"
                    + "category VARCHAR NOT NULL,"
                    + "companyWebsite VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addClubs(){
        try {
            List<Event> events = new ArrayList<>();
            Event event1 = new Event("AusBioInvest 2024", "AUS BioTech", "29th October", "STEM","https://research.qut.edu.au/bridge/events/ausbioinvest-2023/");
            events.add(event1);
            Event event2 = new Event ("Bridge Program Symposium 2024", "QUT", "27-29th October", "Business", "https://research.qut.edu.au/bridge/events/6717/");
            events.add(event2);
            Event event3 = new Event("AusBiotech 2024 National Conference", "AUS BioTech", "30th October - 1st November", "STEM","https://research.qut.edu.au/bridge/events/ausbiotech-2024-national-conference/");
            events.add(event3);
            for(Event event : events) {
                PreparedStatement checkStmt = connection.prepareStatement("Select Count(*) FROM events WHERE eventName = ?");
                checkStmt.setString(1,event.getEventName());
                ResultSet resultSet = checkStmt.executeQuery();
                resultSet.next();
                if(resultSet.getInt(1) == 0) {
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO events (eventName, companyName, eventDate, category, companyWebsite) VALUES (?, ?, ?, ?, ?)");
                    statement.setString(1, event.getEventName());
                    statement.setString(2, event.getCompanyName());
                    statement.setString(3, event.getEventDate());
                    statement.setString(4, event.getCategory());
                    statement.setString(5, event.getCompanyWebsite());
                    statement.executeUpdate();
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        event.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM events";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String eventName = resultSet.getString("eventName");
                String companyName = resultSet.getString("companyName");
                String eventDate = resultSet.getString("eventDate");
                String category = resultSet.getString("category");
                String companyWebsite = resultSet.getString("companyWebsite");
                Event event = new Event(eventName, companyName, eventDate, category, companyWebsite);
                event.setId(id);
                events.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }
    public List<Event> queryEvents(String query) {
        List<Event> events = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String eventName = resultSet.getString("eventName");
                String companyName = resultSet.getString("companyName");
                String eventDate = resultSet.getString("eventDate");
                String category = resultSet.getString("category");
                String companyWebsite = resultSet.getString("companyWebsite");
                Event event = new Event(eventName, companyName, eventDate, category, companyWebsite);
                event.setId(id);
                events.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }
}
