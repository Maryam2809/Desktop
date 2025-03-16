package com.finance.dao;

import com.finance.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserDAO {

    private static final String DB_URL = "jdbc:sqlite:src/main/resources/db/finance.db"; // Path to SQLite database
    private static final String INSERT_USER_QUERY = "INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";

    public boolean saveUser(User user) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement stmt = conn.prepareStatement(INSERT_USER_QUERY);
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword()); // Store encrypted password
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}