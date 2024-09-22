package com.example.addressbook.model.Clubs;

import com.example.addressbook.model.SqliteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteClubDAO {

    private Connection connection;

    public SqliteClubDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
        addClubs();
    }

    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS clubs ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "clubName VARCHAR NOT NULL,"
                    + "category VARCHAR NOT NULL,"
                    + "interests VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addClubs(){
        try {

            List<Club> clubs = new ArrayList<>();
            Club QUTROBOTICSCLUB = new Club("QUTROBOTICSCLUB", "STEM", "Coding");
            clubs.add(QUTROBOTICSCLUB);
            Club QUTSOCIETYOFENTREPRENEURS = new Club("QUTSOCIETYOFENTREPRENEURS", "Career", "Learning");
            clubs.add(QUTSOCIETYOFENTREPRENEURS);
            Club CODENETWORK = new Club("CODENETWORK", "STEM", "Coding");
            clubs.add(CODENETWORK);
            Club QUTREALITYLABS = new Club("QUTREALITYLABS", "STEM", "Coding");
            clubs.add(QUTREALITYLABS);
            Club WOMENINTEHNOLOGYATQUT = new Club("WOMENINTEHNOLOGYATQUT", "STEM", "Coding");
            clubs.add(WOMENINTEHNOLOGYATQUT);
            Club QUTELECTRICALENGINEERINGSOCIETY = new Club("QUTELECTRICALENGINEERINGSOCIETY", "Engineering", "Learning");
            clubs.add(QUTELECTRICALENGINEERINGSOCIETY);
            Club AIESECINQUT = new Club("AIESECINQUT", "Career", "Learning");
            clubs.add(AIESECINQUT);
            Club QUTCHEMISTRYCLUB = new Club("QUTCHEMISTRYCLUB", "STEM", "Learning");
            clubs.add(QUTCHEMISTRYCLUB);
            for(Club club : clubs) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO clubs (clubName, category, interests) VALUES (?, ?, ?)");
                statement.setString(1, club.getClubName());
                statement.setString(2, club.getCategory());
                statement.setString(3, club.getInterests());
                statement.executeUpdate();
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    club.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Club> getAllClubs() {
        List<Club> clubs = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM clubs";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("clubName");
                String password = resultSet.getString("category");
                String email = resultSet.getString("interests");

                Club club = new Club(userName, password, email);
                club.setId(id);
                clubs.add(club);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clubs;
    }
}
