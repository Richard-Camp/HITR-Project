package com.example.addressbook.controller;

import com.example.addressbook.HelloApplication;
import com.example.addressbook.model.User.User;
import com.example.addressbook.model.User.IUserDAO;
import com.example.addressbook.model.User.SqliteUserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class HelloController {
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordField;

    private IUserDAO userDAO = new SqliteUserDAO();

    @FXML
    private Button loginButton;

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        String email = emailTextField.getText();
        String password = passwordField.getText();

        if (isLoginValid(email, password)) {

            Stage stage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
            stage.setScene(scene);
        } else {
            showAlert("Login Failed", "Invalid email or password.");
        }
    }

    private boolean isLoginValid(String email, String password) {
        User user = userDAO.getAllUsers()
                .stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
        return user != null;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
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
            Scene scene = new Scene(root, 800, 600);

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

}