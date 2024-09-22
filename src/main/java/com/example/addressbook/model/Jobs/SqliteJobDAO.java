package com.example.addressbook.model.Jobs;

import com.example.addressbook.model.SqliteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteJobDAO {

    private Connection connection;

    public SqliteJobDAO(List<Job> jobs) {
        connection = SqliteConnection.getInstance();
        createTable();
        addJobs(jobs);
    }

    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS clubs ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "clubName VARCHAR NOT NULL,"
                    + "category VARCHAR NOT NULL"
                    + "interests VARCHAR NOT NULL"
                    + "degree VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addJobs(List<Job> jobs){
        try {
            for(Job job : jobs) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO jobs (clubName, category, interests, degree) VALUES (?, ?, ?, ?)");
                statement.setString(1, job.getJobName());
                statement.setString(2, job.getCategory());
                statement.setString(3, job.getInterests());
                statement.setString(4, job.getDegree());
                statement.executeUpdate();
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    job.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Job> getAllClubs() {
        List<Job> jobs = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM jobs";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("clubName");
                String password = resultSet.getString("category");
                String email = resultSet.getString("interests");
                String degree = resultSet.getString("degree");

                Job job = new Job(userName, password, email, degree);
                job.setId(id);
                jobs.add(job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobs;
    }
}
