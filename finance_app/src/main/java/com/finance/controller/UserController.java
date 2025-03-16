package com.finance.controller;

import com.finance.model.User;
import com.finance.dao.UserDAO;
import com.finance.util.PasswordUtil;

public class UserController {

    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO(); // Initialize DAO (Database Access Object)
    }

    public boolean registerUser(String firstName, String lastName, String email, String password) {
        // Encrypt the password before storing it
        String encryptedPassword = PasswordUtil.encryptPassword(password);

        // Create a new User object
        User newUser = new User(firstName, lastName, email, encryptedPassword);

        // Store the user in the database
        return userDAO.saveUser(newUser);
    }
}