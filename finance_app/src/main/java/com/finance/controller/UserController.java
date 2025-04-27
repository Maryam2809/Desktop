package com.finance.controller;

import com.finance.dao.UserDAO;
import com.finance.model.User;

public class UserController {
    private final UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    // new constructor for testing purposes
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User loginUser(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean registerUser(User user) {
        return userDAO.saveUser(user);
    }
}
