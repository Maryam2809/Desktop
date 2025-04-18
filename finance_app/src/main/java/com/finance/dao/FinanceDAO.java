package com.finance.dao;

import com.finance.model.Expense;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FinanceDAO {
    private static final String DB_URL = "jdbc:sqlite:finance.db";

    private static final String INSERT_EXPENSE_QUERY = "INSERT INTO expenses (user_id, description, amount, category, date, type) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String REMOVE_EXPENSE_QUERY = "DELETE FROM expenses WHERE id = ?";
    private static final String GET_ALL_EXPENSES_QUERY = "SELECT * FROM expenses";

    public void addExpense(Expense expense) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(INSERT_EXPENSE_QUERY)) {
            stmt.setInt(1, 1); // Temporary user_id
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
}