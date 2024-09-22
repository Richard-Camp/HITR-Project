package com.example.addressbook.controller;

import com.example.addressbook.model.User.IUserDAO;
import com.example.addressbook.model.User.SqliteUserDAO;
import com.example.addressbook.model.User.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SignupController {
    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailTextField;
    @FXML
    private ListView<User> contactsListView;

    private IUserDAO userDAO;

    public SignupController() {
        // Replace MockUserDAO with the appropriate implementation
        this.userDAO = new SqliteUserDAO();
    }

    @FXML
    public void initialize() {
        syncContacts();
    }

    private void syncContacts() {
        contactsListView.getItems().clear();
        contactsListView.getItems().addAll(userDAO.getAllUsers());
    }

    @FXML
    private void onSignUp() {
        String username = userNameTextField.getText();
        String password = passwordField.getText();
        String email = emailTextField.getText();

        if (validateInput(username, password, email)) {
            User newUser = new User(username, password, email);
            userDAO.addUser(newUser);
            clearFields();
            syncContacts();
            showAlert("Success", "User signed up successfully!");
        } else {
            showAlert("Error", "Please fill in all fields correctly.");
        }
    }

    private boolean validateInput(String username, String password, String email) {
        return !username.isEmpty() && !password.isEmpty() && !email.isEmpty();
    }

    private void clearFields() {
        userNameTextField.clear();
        passwordField.clear();
        emailTextField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
