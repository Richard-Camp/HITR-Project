package com.example.addressbook.model.Clubs;

import com.example.addressbook.model.SQLiteClubConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteClubDAO {

    private Connection connection;

    private int count = 0;

    public SqliteClubDAO() {
        connection = SQLiteClubConnection.getInstance();
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
                    + "interests VARCHAR NOT NULL,"
                    + "clubImage VARCHAR NOT NULL,"
                    + "clubWebsite VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addClubs(){
        try {

            List<Club> clubs = new ArrayList<>();
            Club QUTROBOTICSCLUB = new Club("QUT Robotics Club", "STEM", "Coding","/images/Club Images/QUTROBOTICSCLUB.png", "https://campus.hellorubric.com/?s=3212");
            clubs.add(QUTROBOTICSCLUB);
            Club QUTSOCIETYOFENTREPRENEURS = new Club("QUT Society of Entrepreneurs", "Career", "Learning", "/images/Club Images/QUTSOCIETYOFENTREPRENEURS.png", "https://campus.hellorubric.com/?s=6262");
            clubs.add(QUTSOCIETYOFENTREPRENEURS);
            Club CODENETWORK = new Club("Code Network", "STEM", "Coding","/images/Club Images/CODENETWORK.png","https://campus.hellorubric.com/?s=4917");
            clubs.add(CODENETWORK);
            Club QUTREALITYLABS = new Club("QUT Reality Labs", "STEM", "Coding","/images/Club Images/QUTREALITYLABS.png","https://campus.hellorubric.com/?s=5017");
            clubs.add(QUTREALITYLABS);
            Club WOMENINTEHNOLOGYATQUT = new Club("Women In Technology At Qut", "STEM", "Coding","/images/Club Images/WOMENINTECHNOLOGYATQUT.png","https://campus.hellorubric.com/?s=1604");
            clubs.add(WOMENINTEHNOLOGYATQUT);
            Club QUTELECTRICALENGINEERINGSOCIETY = new Club("QUT Electrical Engineering Society", "Engineering", "Learning","/images/Club Images/QUTELECTRICALENGINEERINGSTUDENTSOCIETY.png","https://campus.hellorubric.com/?s=1457");
            clubs.add(QUTELECTRICALENGINEERINGSOCIETY);
            Club AIESECINQUT = new Club("AIESEC In QUT", "Career", "Learning","/images/Club Images/AIESECINQUT.png","https://campus.hellorubric.com/?s=3896");
            clubs.add(AIESECINQUT);
            Club QUTCHEMISTRYCLUB = new Club("QUT Chemistry Club", "STEM", "Learning","/images/Club Images/QUTCHEMISTRYCLUB.png","https://campus.hellorubric.com/?s=5514");
            clubs.add(QUTCHEMISTRYCLUB);
            for(Club club : clubs) {
                PreparedStatement checkStmt = connection.prepareStatement("Select Count(*) FROM clubs WHERE clubName = ?");
                checkStmt.setString(1,club.getClubName());
                ResultSet resultSet = checkStmt.executeQuery();
                resultSet.next();
                if(resultSet.getInt(1) == 0) {
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO clubs (clubName, category, interests, clubImage, clubWebsite) VALUES (?, ?, ?, ?, ?)");
                    statement.setString(1, club.getClubName());
                    statement.setString(2, club.getCategory());
                    statement.setString(3, club.getInterests());
                    statement.setString(4, club.getClubImage());
                    statement.setString(5, club.getClubWebsite());
                    statement.executeUpdate();
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        club.setId(generatedKeys.getInt(1));
                    }
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
                String clubName = resultSet.getString("clubName");
                String category = resultSet.getString("category");
                String interests = resultSet.getString("interests");
                String clubImage = resultSet.getString("clubImage");
                String clubWebsite = resultSet.getString("clubWebsite");
                Club club = new Club(clubName, category, interests, clubImage, clubWebsite);
                club.setId(id);
                clubs.add(club);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clubs;
    }
}
