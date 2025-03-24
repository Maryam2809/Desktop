package com.finance.controller;

import com.finance.model.User;
import com.finance.dao.UserDAO;
import com.finance.util.PasswordUtil;

public class UserController {
    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    public boolean registerUser(String firstName, String lastName, String email, String password) {
        String encryptedPassword = PasswordUtil.encryptPassword(password);
        User newUser = new User(firstName, lastName, email, encryptedPassword);
        return userDAO.saveUser(newUser);
    }

    public boolean loginUser(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        if (user != null) {
            return PasswordUtil.checkPassword(password, user.getPassword());
        }
        return false;
    }
}