package com.example.addressbook.controller;

import com.example.addressbook.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Hyperlink;
import javafx.stage.Modality;
import javafx.scene.Parent;

import java.io.IOException;

public class HelloController {


    @FXML
    private Hyperlink joinNowLink;

    @FXML
    public void handleJoinNow() {
        try {
            Stage signupStage = new Stage();
            signupStage.initModality(Modality.APPLICATION_MODAL);
            signupStage.setTitle("Sign Up");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/addressbook/signup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 500, 600);
            // Set the stage to be non-resizable
            signupStage.setResizable(true);
            signupStage.setScene(scene);
            signupStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Hyperlink joinNowLinkk;

    @FXML
    public void handleJoinNoww() {
        try {
            Stage signupStage = new Stage();
            signupStage.initModality(Modality.APPLICATION_MODAL);
            signupStage.setTitle("Sign Up");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/addressbook/test.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 500, 600);
            signupStage.setScene(scene);
            signupStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private Button loginButton;
    @FXML
    protected void onLoginButtonClick() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    private Button homeButton;
    @FXML
    protected void onHomeButtonClick() throws IOException {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

}
