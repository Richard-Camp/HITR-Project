package com.example.addressbook.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class SignupController {

    @FXML
    private ImageView imageView;

    @FXML
    public void initialize() {
        Image image = new Image("/images/nord.png");
        imageView.setImage(image);
    }

    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    @FXML
    public void handleSubmit() {
        // Handle the signup logic here
        System.out.println("Username: " + usernameField.getText());
        System.out.println("Email: " + emailField.getText());
        System.out.println("Password: " + passwordField.getText());

        // Close the signup dialog
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }
}