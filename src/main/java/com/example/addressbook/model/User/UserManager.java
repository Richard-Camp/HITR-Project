package com.example.addressbook.model.User;

import java.util.List;

public class UserManager {
    private IUserDAO userDAO;
    public UserManager(IUserDAO userDAO) {this.userDAO = userDAO;}

    public List<User> searchUsers(String query) {
        return userDAO.getAllUsers()
                .stream()
                .filter(user -> isUserMatched(user, query))
                .toList();
    }

    private boolean isUserMatched(User user, String query) {
        if (query == null || query.isEmpty()) return true;
        query = query.toLowerCase();
        String searchString = user.getUserName()
                + " " + user.getEmail()
                + " " + user.getPassword();
        return searchString.toLowerCase().contains(query);
    }

    public void addUser(User user) {userDAO.addUser(user);}

    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}