package com.finance.dao;

import com.finance.model.User;
import java.sql.*;

public class UserDAO {
    private static final String DB_URL = "jdbc:sqlite:finance.db";

    private static final String INSERT_USER_QUERY = "INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
    private static final String GET_USER_QUERY = "SELECT * FROM users WHERE email = ?";

    public boolean saveUser(User user) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement stmt = conn.prepareStatement(INSERT_USER_QUERY);
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByEmail(String email) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(GET_USER_QUERY)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}