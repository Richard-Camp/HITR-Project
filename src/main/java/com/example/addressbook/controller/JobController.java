package com.example.addressbook.controller;

import com.example.addressbook.HelloApplication;
import com.example.addressbook.model.Jobs.Job;
import com.example.addressbook.model.Jobs.JobManager;
import com.example.addressbook.model.Jobs.SqliteJobDAO;

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
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class JobController {
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchBar;
    @FXML
    private TextField salaryBar;
    @FXML
    private CheckBox healthCheck;
    @FXML
    private CheckBox educationCheck;
    @FXML
    private CheckBox itCheck;
    @FXML
    private CheckBox businessCheck;
    @FXML
    private CheckBox engineeringCheck;
    @FXML
    private CheckBox legalCheck;

    @FXML
    private CheckBox bachelorCheck;
    @FXML
    private CheckBox mastersCheck;
    @FXML
    private CheckBox doctorateCheck;

    private JobManager jobManager;

    @FXML
    private FlowPane paneFlowPane;

    public JobController(){
        SqliteJobDAO jobDAO = new SqliteJobDAO();
        jobManager = new JobManager(jobDAO);
    }

    public void initialize() {
        displayJobs(jobManager.searchJobs(null));
    }

    private void displayJobs(List<Job> jobs){
        paneFlowPane.getChildren().clear();
        for (Job job : jobs){
            Pane jobPane = createJobPane(job);
            paneFlowPane.getChildren().add(jobPane);
        }
    }

    private Pane createJobPane(Job job){
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #FFFFFF");
        pane.setPrefHeight(270);
        pane.setPrefWidth(350);

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        Font labels = new Font(16);

        javafx.scene.control.Label jobNameLabel = new Label(job.getJobName());
        javafx.scene.control.Label companyNameLabel = new Label(job.getCompany());
        javafx.scene.control.Label salaryLabel = new Label("Estimated Annual Salary: $" + job.getSalary().toString());
        javafx.scene.control.TextArea description = new javafx.scene.control.TextArea(job.getDescription());

        description.setWrapText(true);
        description.setPrefWidth(350);
        description.setPrefHeight(95);

        jobNameLabel.setFont(new Font(24));
        companyNameLabel.setFont(labels);
        salaryLabel.setFont(labels);

        Button companyWebsite = new Button();
        companyWebsite.setText("Apply Here");
        companyWebsite.setStyle("-fx-background-color: #663399; -fx-text-fill: #FFFFFF;");
        companyWebsite.setOnAction(event -> {
            try {
                URI url = new URI(job.getCompanyWebsite());

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(url);
                } else {
                    System.out.println("Desktop is not supported. Cannot open the link.");
                }
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });


        vbox.getChildren().addAll(jobNameLabel, companyNameLabel, salaryLabel, description, companyWebsite);

        pane.getChildren().add(vbox);

        return pane;
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

    public void healthClick() {

    }

    public void educationClick() {
    }

    public void itClick() {
    }

    public void businessClick() {
    }

    public void engineeringClick() {
    }

    public void legalClick() {
    }

    public void bachelorClick() {
    }

    public void mastersClick() {
    }

    public void doctorateClick() {
    }

    public void clickSearch() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM jobs WHERE ");
        boolean degreeCheck = false;

        if(bachelorCheck.isSelected()||mastersCheck.isSelected()||doctorateCheck.isSelected()) {
            query.append("(");

            if (bachelorCheck.isSelected()) {
                query.append("degree = 'Bachelor'");
                degreeCheck = true;
            }
            if (mastersCheck.isSelected()) {
                if (degreeCheck) {
                    query.append(" OR ");
                }
                query.append("degree = 'Masters'");
                degreeCheck = true;
            }
            if (doctorateCheck.isSelected()) {
                if (degreeCheck) {
                    query.append(" OR ");
                }
                query.append("degree = 'Doctorate/PHD'");
                degreeCheck = true;
            }
            query.append(")");
        }
            boolean categoryCheck = false;
        if(healthCheck.isSelected()||educationCheck.isSelected()||itCheck.isSelected()||businessCheck.isSelected()||engineeringCheck.isSelected()||legalCheck.isSelected()){
            if(degreeCheck){
                query.append(" AND ");
            }
            query.append("(");

            if (healthCheck.isSelected()) {
                query.append("category LIKE '%Health Care%'");
                categoryCheck = true;
            }
            if (educationCheck.isSelected()) {
                if(categoryCheck){query.append(" OR ");}
                query.append("category LIKE '%Education%'");
                categoryCheck = true;
            }
            if (itCheck.isSelected()) {
                if(categoryCheck){query.append(" OR ");}
                query.append("category LIKE '%IT%'");
                categoryCheck = true;
            }
            if (businessCheck.isSelected()) {
                if(categoryCheck){query.append(" OR ");}
                query.append("category LIKE '%Finance & Business%'");
                categoryCheck = true;
            }
            if (engineeringCheck.isSelected()) {
                if(categoryCheck){query.append(" OR ");}
                query.append("category LIKE '%Engineering%'");
                categoryCheck = true;
            }
            if (legalCheck.isSelected()) {
                if(categoryCheck){query.append(" OR ");}
                query.append("category = 'Legal Professions'");
                categoryCheck = true;
            }
            query.append(")");
        }
        if(degreeCheck||categoryCheck){query.append(" AND ");}
        query.append("jobName LIKE '%");
        query.append(searchBar.getText());
        query.append("%'");
        if(validateSalaryBar()){
            query.append(" AND ");
            Integer minsalary = 0;
            if(!(salaryBar.getText() == null || Objects.equals(salaryBar.getText(), ""))){
                minsalary = Integer.parseInt(salaryBar.getText());
            }

            query.append("salary >= ");
            query.append(minsalary);
        }

        List<Job> checkJobs = jobMan    ager.getJobs(query.toString());
        displayJobs(checkJobs);

    }
    private boolean validateSalaryBar(){
        try {
            if(salaryBar.getText() == null || Objects.equals(salaryBar.getText(), "")){return true;}
            Integer minsalary = Integer.parseInt(salaryBar.getText());
            if(minsalary < 0){showAlert("error", "Please Enter Positive Numbers Only"); return false;}
            return true;
        } catch (NumberFormatException e){
            showAlert("error", "Please Enter Valid Salary (Numbers Only)");
            return false;
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
