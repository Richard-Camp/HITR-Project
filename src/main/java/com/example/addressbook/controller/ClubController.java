package com.example.addressbook.controller;

import com.example.addressbook.HelloApplication;
import com.example.addressbook.model.Clubs.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class ClubController {

    private ClubManager clubManager;

    @FXML
    private RadioButton EngineeringRadioBox;
    @FXML
    private RadioButton STEMRadioBox;
    @FXML
    private RadioButton CareerRadioBox;
    @FXML
    private ToggleButton clubButton;
    @FXML
    private FlowPane VboxFlowPane;
    @FXML
    private TextField searchBar;
    @FXML
    private Button searchButton;

    public ClubController(){
        SqliteClubDAO clubDAO = new SqliteClubDAO();
        clubManager = new ClubManager(clubDAO);
    }

    public void initialize() {
        displayClubs(clubManager.searchClubs(null));
    }


    private void displayClubs(List<Club> clubs){
        VboxFlowPane.getChildren().clear();
        for (Club club : clubs){
            VBox clubVBox = createClubVBox(club);
            VboxFlowPane.getChildren().add(clubVBox);
        }
    }

    private VBox createClubVBox(Club club){

        VBox vbox = new VBox();

        Button imageButton = new Button();
        imageButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        String imagePath = club.getClubImage();
        URL imageURL = getClass().getResource(imagePath);
        if(imageURL == null){return vbox;}
        ImageView imageView = new ImageView(new Image(imageURL.toExternalForm()));
        imageView.setFitHeight(160);
        imageView.setFitWidth(160);
        imageButton.setGraphic(imageView);

        imageButton.setOnAction(event -> {
            try {
                URI url = new URI(club.getClubWebsite());

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(url);
                } else {
                    System.out.println("Desktop is not supported. Cannot open the link.");
                }
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        Label clubNameLabel = new Label(club.getClubName());

        vbox.getChildren().addAll(imageButton,clubNameLabel);

        return vbox;
    }


    @FXML
    protected void EngineeringCheck(){
        if(EngineeringRadioBox.isSelected()){
            STEMRadioBox.setSelected(false);
            CareerRadioBox.setSelected(false);
            displayClubs(clubManager.searchClubs("engineering"));
        }
        else {
            displayClubs(clubManager.searchClubs(null));
        }
    }
    @FXML
    protected void STEMCheck(){
        if(STEMRadioBox.isSelected()){
            EngineeringRadioBox.setSelected(false);
            CareerRadioBox.setSelected(false);
            displayClubs(clubManager.searchClubs("stem"));
        }
        else {
            displayClubs(clubManager.searchClubs(null));
        }
    }
    @FXML
    protected void CareerCheck() {
        if(CareerRadioBox.isSelected()){
            STEMRadioBox.setSelected(false);
            EngineeringRadioBox.setSelected(false);
            displayClubs(clubManager.searchClubs("career"));
        }
        else {
            displayClubs(clubManager.searchClubs(null));
        }
    }
    @FXML
    public void onSearchClick() {
        displayClubs(clubManager.searchClubs(searchBar.getText()));
    }

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
