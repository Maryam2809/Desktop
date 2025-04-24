package com.finance.dao;

import com.finance.model.Expense;
import com.finance.model.Goal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FinanceDAO {
    private static final String DB_URL = "jdbc:sqlite:finance.db";

    private static final String INSERT_EXPENSE_QUERY = "INSERT INTO expenses (user_id, description, amount, category, date, type) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String REMOVE_EXPENSE_QUERY = "DELETE FROM expenses WHERE id = ?";
    private static final String GET_ALL_EXPENSES_QUERY = "SELECT * FROM expenses";
    private static final String GET_TOTAL_EXPENSES_QUERY = "SELECT SUM(amount) as total FROM expenses WHERE type = 'Expense'";
    private static final String GET_TOTAL_SAVINGS_QUERY = "SELECT SUM(amount) as total FROM expenses WHERE type = 'Saving'";
    private static final String GET_GOALS_QUERY = "SELECT * FROM goals";
    private static final String GET_RECENT_EXPENSES_QUERY = "SELECT * FROM expenses ORDER BY date DESC LIMIT 5;";

    public List<Expense> getFilteredExpenses(String category, String type, String date) {
        List<Expense> expenses = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM expenses WHERE 1=1");

        if (!"All".equals(category)) {
            query.append(" AND category = '").append(category).append("'");
        }
        if (!"All".equals(type)) {
            query.append(" AND type = '").append(type).append("'");
        }
        if (date != null && !date.isEmpty()) {
            query.append(" AND date = '").append(date).append("'");
        }

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query.toString())) {

            while (rs.next()) {
                Expense expense = new Expense(
                        rs.getString("description"),
                        rs.getDouble("amount"),
                        rs.getString("category"),
                        rs.getString("date"),
                        rs.getString("type")
                );
                expense.setId(rs.getInt("id"));
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    public List<Expense> getRecentExpenses(){
        List<Expense> expenses = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(GET_RECENT_EXPENSES_QUERY)) {

            while (rs.next()) {
                Expense expense = new Expense(
                        rs.getString("description"),
                        rs.getDouble("amount"),
                        rs.getString("category"),
                        rs.getString("date"),
                        rs.getString("type")
                );
                expense.setId(rs.getInt("id"));
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    public void addExpense(Expense expense) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(INSERT_EXPENSE_QUERY)) {
            stmt.setInt(1, 1);
            stmt.setString(2, expense.getDescription());
            stmt.setDouble(3, expense.getAmount());
            stmt.setString(4, expense.getCategory());
            stmt.setString(5, expense.getDate());
            stmt.setString(6, expense.getType());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeExpense(int expenseId) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(REMOVE_EXPENSE_QUERY)) {
            stmt.setInt(1, expenseId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(GET_ALL_EXPENSES_QUERY)) {

            while (rs.next()) {
                Expense expense = new Expense(
                        rs.getString("description"),
                        rs.getDouble("amount"),
                        rs.getString("category"),
                        rs.getString("date"),
                        rs.getString("type")
                );
                expense.setId(rs.getInt("id"));
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    public double getTotalExpenses() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(GET_TOTAL_EXPENSES_QUERY);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getDouble("total") : 0.0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public double getTotalSavings() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(GET_TOTAL_SAVINGS_QUERY);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getDouble("total") : 0.0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public List<Goal> getGoals() {
        List<Goal> goals = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(GET_GOALS_QUERY)) {

            while (rs.next()) {
                Goal goal = new Goal(
                        rs.getString("name"),
                        rs.getDouble("spending_limit"),
                        rs.getInt("duration")
                );
                goals.add(goal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goals;
    }
}