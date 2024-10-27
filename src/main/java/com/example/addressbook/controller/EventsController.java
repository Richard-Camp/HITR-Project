package com.example.addressbook.controller;

import com.example.addressbook.HelloApplication;
import com.example.addressbook.model.Events.EventManager;
import com.example.addressbook.model.Events.SqliteEventDAO;
import com.example.addressbook.model.Events.Event;
import com.example.addressbook.model.Internships.Internship;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class EventsController {
    private EventManager eventManager;

    public EventsController(){
        SqliteEventDAO eventDAO = new SqliteEventDAO();
        eventManager = new EventManager(eventDAO);
    }

    @FXML
    private FlowPane paneFlowPane;
    @FXML
    private CheckBox healthCheck;
    @FXML
    private CheckBox educationCheck;
    @FXML
    private CheckBox stemCheck;
    @FXML
    private CheckBox businessCheck;
    @FXML
    private CheckBox engineeringCheck;
    @FXML
    private CheckBox legalCheck;
    @FXML
    private TextField searchBar;
    @FXML
    private Button searchButton;


    @FXML
    public void initialize(){
        displayEvents(eventManager.searchEvents(null));
    }

    private void displayEvents(List<Event> events){
        paneFlowPane.getChildren().clear();
        for (Event event : events){
            Pane eventPane = createEventPane(event);
            paneFlowPane.getChildren().add(eventPane);
        }
    }

    private Pane createEventPane(Event event){
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: lightblue");
        pane.setPrefHeight(240);
        pane.setPrefWidth(350);

        VBox vbox = new VBox();
        vbox.setSpacing(20);


        Font labels = new Font(24);

        javafx.scene.control.Label eventNameLabel = new Label(event.getEventName());
        javafx.scene.control.Label companyNameLabel = new Label(event.getCompanyName());

        eventNameLabel.setFont(labels);
        eventNameLabel.setStyle("-fx-font-weight: bold;");
        eventNameLabel.setWrapText(true);
        eventNameLabel.setPrefWidth(350);
        eventNameLabel.setAlignment(Pos.CENTER);
        eventNameLabel.setTextAlignment(TextAlignment.CENTER);
        companyNameLabel.setFont(new Font(16));

        Button companyWebsite = new Button();
        companyWebsite.setText("Apply Here");
        companyWebsite.setStyle("-fx-background-color: #663399; -fx-text-fill: #FFFFFF;");
        companyWebsite.setOnAction(actionevent -> {
            try {
                URI url = new URI(event.getCompanyWebsite());

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(url);
                } else {
                    System.out.println("Desktop is not supported. Cannot open the link.");
                }
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        vbox.getChildren().addAll(eventNameLabel, companyNameLabel, companyWebsite);
        vbox.setAlignment(Pos.CENTER);
        pane.getChildren().add(vbox);
        return pane;
    }

    @FXML
    protected void onSearchClick() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM events WHERE ");
        boolean categoryCheck = false;

        if(engineeringCheck.isSelected()||stemCheck.isSelected()||businessCheck.isSelected()||healthCheck.isSelected()||educationCheck.isSelected()||legalCheck.isSelected()) {
            query.append("(");

            if (engineeringCheck.isSelected()) {
                query.append("category = 'Engineering'");
                categoryCheck = true;
            }
            if (stemCheck.isSelected()) {
                if (categoryCheck) {
                    query.append(" OR ");
                }
                query.append("category = 'STEM'");
                categoryCheck = true;
            }
            if (businessCheck.isSelected()) {
                if (categoryCheck) {
                    query.append(" OR ");
                }
                query.append("category = 'Business'");
                categoryCheck = true;
            }
            if (healthCheck.isSelected()) {
                if (categoryCheck) {
                    query.append(" OR ");
                }
                query.append("category = 'Health Care'");
                categoryCheck = true;
            }
            if (legalCheck.isSelected()) {
                if (categoryCheck) {
                    query.append(" OR ");
                }
                query.append("category = 'legal'");
                categoryCheck = true;
            }
            if (educationCheck.isSelected()) {
                if (categoryCheck) {
                    query.append(" OR ");
                }
                query.append("category = 'education'");
                categoryCheck = true;
            }
            query.append(")");
        }
        if(categoryCheck){query.append(" AND ");}
        query.append("eventName LIKE '%");
        query.append(searchBar.getText());
        query.append("%'");

        List<Event> checkEvents = eventManager.getEvents(query.toString());
        displayEvents(checkEvents);
    }

    @FXML
    private ToggleButton clubButton;
    @FXML
    protected void onClubButtonClick() throws IOException {
        Stage stage = (Stage) clubButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("clubs-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    private ToggleButton resumeButton;
    @FXML
    protected void onResumeButtonClick() throws IOException {
        Stage stage = (Stage) resumeButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("resume-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    private ToggleButton tipsButton;
    @FXML
    protected void onCareerTipsButtonClick() throws IOException {
        Stage stage = (Stage) tipsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("careerTips-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    private ToggleButton jobsButton;
    @FXML
    protected void onJobsButtonClick() throws IOException {
        Stage stage = (Stage) jobsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("job-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    private ToggleButton networkingButton;
    @FXML
    protected void onNetworkingButtonClick() throws IOException {
        Stage stage = (Stage) networkingButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("events-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    private ToggleButton alumniNetworkButton;
    @FXML
    protected void onAlumniButtonClick() throws IOException {
        Stage stage = (Stage) alumniNetworkButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("network-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    private ToggleButton internshipButton;
    @FXML
    protected void onInternshipButtonClick() throws IOException {
        Stage stage = (Stage) internshipButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("internships-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    private ToggleButton logoutButton;
    @FXML
    protected void onLogOutButtonClick() throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    private ToggleButton feedbackButton;
    @FXML
    protected void onFeedbackButtonClick() throws IOException {
        Stage stage = (Stage) feedbackButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("feedback-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
}
