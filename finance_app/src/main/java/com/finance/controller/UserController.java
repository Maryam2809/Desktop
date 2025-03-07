package com.finance.controller;

import com.finance.security.Security;

public class UserController {
    private UserDatabase userDatabase;

    public UserController() {
        this.userDatabase = new UserDatabase(DatabaseConfig.getConnection());
    }

    public boolean login(String email, String password) {
        String hashedPassword = Security.hashPassword(password);
        User user = userDatabase.loginUser(email, hashedPassword);
        return user != null;
    }
}