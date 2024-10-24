package com.example.addressbook.model.Internships;


import com.example.addressbook.model.Jobs.Job;
import com.example.addressbook.model.SQLiteInternshipConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteInternshipDAO {
    private Connection connection;

    private int count = 0;

    public SqliteInternshipDAO() {
        connection = SQLiteInternshipConnection.getInstance();
        createTable();
        addClubs();
    }

    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS internships ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "internshipName VARCHAR NOT NULL,"
                    + "companyName VARCHAR NOT NULL,"
                    + "category VARCHAR NOT NULL,"
                    + "description VARCHAR NOT NULL,"
                    + "internshipWebsite VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addClubs(){
        try {
            List<Internship> internships = new ArrayList<>();
            Internship business = new Internship("Summer Internship – Economics and Advisory", "Synergies", "Business", "An exciting opportunity exists for a student to undertake a paid four-week internship with the firm over the summer break period (December 2024 to February 2025). Working closely with experienced microeconomists, you will experience first-hand the skills that are required to be successful in this field and will be given the opportunity to apply and expand your skills. ", "https://www.seek.com.au/Synergies-Economic-Consulting-jobs/at-this-company");
            internships.add(business);
            Internship engineering = new Internship("Abergeldie Summer Internship Program", "Abergeldie", "Engineering", "Kick-start your career with Abergeldie, where you'll gain hands-on experience, learn from industry experts, and work on meaningful projects that help build better communities. Our Summer Internship Program offers university students the opportunity to develop essential skills and networks, preparing you for a successful career after graduation.", "https://www.seek.com.au/Abergeldie-jobs/at-this-company");
            internships.add(engineering);
            Internship STEM = new Internship("2024 Software Dev Engineer Intern - Brisbane or Sydney, Stores", "Amazon", "STEM", "We are looking for passionate developers who  love solving challenging problems, learning on the job, and working in a  team to get stuff done. You'll work on all phases of the software  lifecycle alongside a diverse team. Your fresh perspective will inform  innovative solutions to problems customers' face every day.", "https://www.seek.com.au/engineering-internship-jobs/in-All-Brisbane-QLD?jobId=79589256&type=standout");
            internships.add(STEM);
            Internship other = new Internship("Intern(Legal)", "Crime and Corruption Commission", "Other", "The primary purpose of this role is to work as a member of the agency and support senior lawyers. Preparing and reviewing draft advice, presentations, and communications on a range of legal matters, including regulatory, commercial and criminal issues for the review of qualified senior lawyers.", "https://www.seek.com.au/Crime-and-Corruption-Commission-QLD-jobs/at-this-company");
            internships.add(other);

            for(Internship internship : internships) {
                PreparedStatement checkStmt = connection.prepareStatement("Select Count(*) FROM internships WHERE internshipName = ?");
                checkStmt.setString(1,internship.getInternshipName());
                ResultSet resultSet = checkStmt.executeQuery();
                resultSet.next();
                if(resultSet.getInt(1) == 0) {
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO internships (internshipName, companyName, category, description, internshipWebsite) VALUES (?, ?, ?, ?, ?)");
                    statement.setString(1, internship.getInternshipName());
                    statement.setString(2, internship.getCompanyName());
                    statement.setString(3, internship.getCategory());
                    statement.setString(4, internship.getDescription());
                    statement.setString(5, internship.getInternshipWebsite());
                    statement.executeUpdate();
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        internship.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Internship> getAllInternships() {
        List<Internship> internships = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM internships";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String internshipName = resultSet.getString("internshipName");
                String companyName = resultSet.getString("companyName");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                String internshipWebsite = resultSet.getString("internshipWebsite");
                Internship internship = new Internship(internshipName, companyName, category, description, internshipWebsite);
                internship.setId(id);
                internships.add(internship);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return internships;
    }
    public List<Internship> queryInternships(String query) {
        List<Internship> internships = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String internshipName = resultSet.getString("internshipName");
                String companyName = resultSet.getString("companyName");
                String category = resultSet.getString("category");
                String internshipWebsite = resultSet.getString("internshipWebsite");
                String description = resultSet.getString("description");

                Internship internship = new Internship(internshipName, companyName, category, description, internshipWebsite);
                internship.setId(id);
                internships.add(internship);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return internships;
    }
}
