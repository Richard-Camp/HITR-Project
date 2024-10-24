package com.example.addressbook.controller;

import com.example.addressbook.HelloApplication;
import com.example.addressbook.model.Internships.Internship;
import com.example.addressbook.model.Internships.InternshipManager;
import com.example.addressbook.model.Internships.SqliteInternshipDAO;
import com.example.addressbook.model.Jobs.Job;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

public class InternshipController {
    private InternshipManager internshipManager;

    public InternshipController(){
        SqliteInternshipDAO internshipDAO = new SqliteInternshipDAO();
        internshipManager = new InternshipManager(internshipDAO);
    }
    @FXML
    private FlowPane paneFlowPane;
    @FXML
    private TextField searchBar;
    @FXML
    private Button searchButton;
    @FXML
    private RadioButton EngineeringRadioBox;
    @FXML
    private RadioButton STEMRadioBox;
    @FXML
    private RadioButton BusinessRadioBox;
    @FXML
    private RadioButton OtherRadioBox;

    @FXML
    public void initialize(){
        displayInternships(internshipManager.searchInternships(null));
    }

    private void displayInternships(List<Internship> internships){
        paneFlowPane.getChildren().clear();
        for (Internship internship : internships){
            Pane internshipPane = createIntershipPane(internship);
            paneFlowPane.getChildren().add(internshipPane);
        }
    }

    private Pane createIntershipPane(Internship internship){
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: lightblue");
        pane.setPrefHeight(240);
        pane.setPrefWidth(350);

        VBox vbox = new VBox();
        vbox.setSpacing(10);


        Font labels = new Font(16);

        javafx.scene.control.Label internshipNameLabel = new Label(internship.getInternshipName());
        javafx.scene.control.Label companyNameLabel = new Label(internship.getCompanyName());

        javafx.scene.control.TextArea description = new javafx.scene.control.TextArea(internship.getDescription());

        description.setWrapText(true);
        description.setPrefWidth(350);
        description.setPrefHeight(95);

        internshipNameLabel.setFont(new Font(16));
        internshipNameLabel.setStyle("-fx-font-weight: bold;");
        internshipNameLabel.setWrapText(true);
        internshipNameLabel.setPrefWidth(350);
        internshipNameLabel.setAlignment(Pos.CENTER);
        internshipNameLabel.setTextAlignment(TextAlignment.CENTER);
        companyNameLabel.setFont(new Font(16));

        Button companyWebsite = new Button();
        companyWebsite.setText("Apply Here");
        companyWebsite.setStyle("-fx-background-color: #663399; -fx-text-fill: #FFFFFF;");
        companyWebsite.setOnAction(event -> {
            try {
                URI url = new URI(internship.getInternshipWebsite());

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(url);
                } else {
                    System.out.println("Desktop is not supported. Cannot open the link.");
                }
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        vbox.getChildren().addAll(internshipNameLabel, companyNameLabel, description, companyWebsite);
        vbox.setAlignment(Pos.CENTER);
        pane.getChildren().add(vbox);
        return pane;
    }
    @FXML
    protected void onSearchClick() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM internships WHERE ");
        boolean categoryCheck = false;

        if(EngineeringRadioBox.isSelected()||STEMRadioBox.isSelected()||BusinessRadioBox.isSelected()||OtherRadioBox.isSelected()) {
            query.append("(");

            if (EngineeringRadioBox.isSelected()) {
                query.append("category = 'Engineering'");
                categoryCheck = true;
            }
            if (STEMRadioBox.isSelected()) {
                if (categoryCheck) {
                    query.append(" OR ");
                }
                query.append("category = 'STEM'");
                categoryCheck = true;
            }
            if (BusinessRadioBox.isSelected()) {
                if (categoryCheck) {
                    query.append(" OR ");
                }
                query.append("category = 'Business'");
                categoryCheck = true;
            }
            if (OtherRadioBox.isSelected()) {
                if (categoryCheck) {
                    query.append(" OR ");
                }
                query.append("category = 'Other'");
                categoryCheck = true;
            }
            query.append(")");
        }
        if(categoryCheck){query.append(" AND ");}
        query.append("internshipName LIKE '%");
        query.append(searchBar.getText());
        query.append("%'");

        List<Internship> checkInternships = internshipManager.getInternships(query.toString());
        displayInternships(checkInternships);
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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("internship-view.fxml"));
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
