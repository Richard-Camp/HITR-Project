package com.example.addressbook;
import com.example.addressbook.model.Clubs.Club;
import com.example.addressbook.model.Clubs.SqliteClubDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    public static final String TITLE = "CareerLaunch";
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Club QUTROBOTICSCLUB = new Club("QUTROBOTICSCLUB", "STEM", "Coding");
        Club QUTSOCIETYOFENTREPRENEURS = new Club("QUTSOCIETYOFENTREPRENEURS", "Career", "Learning");
        List<Club> clubs = new ArrayList<>();
        SqliteClubDAO clubDAO = new SqliteClubDAO(clubs);
    }
}