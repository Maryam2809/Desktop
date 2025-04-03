package com.finance.dao;

import com.finance.config.DatabaseConfig;
import com.finance.model.Goal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoalDAO {
    public void insertGoal(Goal goal) {
        String sql = "INSERT INTO goals (name, spending_limit, duration) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, goal.getName());
            pstmt.setDouble(2, goal.getSpendingLimit());
            pstmt.setInt(3, goal.getDuration());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGoal(String goalName) {
        String sql = "DELETE FROM goals WHERE name = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, goalName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Goal> getAllGoals() {
        List<Goal> goals = new ArrayList<>();
        String sql = "SELECT * FROM goals";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                goals.add(new Goal(
                        rs.getString("name"),
                        rs.getDouble("spending_limit"),
                        rs.getInt("duration")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return goals;
    }
}