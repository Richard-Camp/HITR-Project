package com.example.addressbook.controller;

import com.example.addressbook.HelloApplication;
import com.example.addressbook.model.Clubs.*;
import com.example.addressbook.model.User.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClubController {
    ClubManager clubManager;

    public ClubController() {
        clubManager = new ClubManager(new SqliteClubDAO());
    }
    @FXML
    private VBox QUTROBOTICSCLUB;
    @FXML
    private VBox QUTSOCIETYOFENTREPRENEURS;
    @FXML
    private VBox CODENETWORK;
    @FXML
    private VBox QUTREALITYLABS;
    @FXML
    private VBox WOMENINTECHNOLOGYATQUT;
    @FXML
    private VBox QUTELECTRICALENGINEERINGSTUDENTSOCIETY;
    @FXML
    private VBox AIESECINQUT;
    @FXML
    private VBox QUTCHEMISTRYCLUB;
    @FXML
    private CheckBox EngineeringCheckBox;
    @FXML
    private CheckBox STEMCheckBox;
    @FXML
    private CheckBox CareerCheckBox;
    @FXML
    private CheckBox CodingCheckBox;

    @FXML
    private CheckBox ReadingCheckBox;
    @FXML
    private CheckBox WritingCheckBox;
    @FXML
    private CheckBox LearningCheckBox;
    @FXML
    private VBox ClubGrid;
    @FXML
    protected void EngineeringCheck(){}
    @FXML
    protected void STEMCheck(){}
    @FXML
    protected void CareerCheck(){}
    @FXML
    protected void LearningCheck(){}
    @FXML
    protected void ReadingCheck(){}
    @FXML
    protected void CodingCheck(){}
    @FXML
    protected void WritingCheck(){}
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
