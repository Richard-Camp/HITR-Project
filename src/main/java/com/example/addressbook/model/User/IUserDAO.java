package com.example.addressbook.model.User;

import java.util.List;

/**
 * Interface for the user Data Access Object that handles
 * the CRUD operations for the user class with the database.
 */
public interface IUserDAO {
    /**
     * Adds a new user to the database.
     * @param user The user to add.
     */
    public void addUser(User user);
    /**
     * Updates an existing user in the database.
     * @param user The user to update.
     */
    public void updateUser(User user);
    /**
     * Deletes a contact from the database.
     * @param user The contact to delete.
     */
    public void deleteUser(User user);
    /**
     * Retrieves all users from the database.
     * @return A list of all users in the database.
     */
    public User getUser(int id);

    public List<User> getAllUsers();
}