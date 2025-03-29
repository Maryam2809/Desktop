package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ExpenseDAO {

    private static final String URL = "jdbc:sqlite:src/main/resources/db/expenses.db";
    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS expense (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "amount INTEGER NOT NULL, " +
                    "category TEXT NOT NULL, " +
                    "date TEXT NOT NULL" +
                    ");";

    public boolean saveExpense(Expense expense) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            // Create table if doesnt exist
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(CREATE_TABLE_QUERY);
            }

            String INSERT_EXPENSE_QUERY = "INSERT INTO expense (name, amount, category, date) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(INSERT_EXPENSE_QUERY);
            stmt.setString(1, expense.getName());
            stmt.setInt(2, expense.getAmount());
            stmt.setString(3, expense.getCategory());
            stmt.setString(4, expense.getDate().toString());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Map<String, Integer> getExpensesByCategory() {
        Map<String, Integer> totals = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(URL)) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(CREATE_TABLE_QUERY);
            }

            String GET_EXPENSE_QUERY = "SELECT category, SUM(amount) as total FROM expense GROUP BY category";
            PreparedStatement stmt = conn.prepareStatement(GET_EXPENSE_QUERY);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                totals.put(rs.getString("category"), rs.getInt("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totals;
    }

    public Map<Integer, Integer> getMonthsExpenses() {
        Map<Integer, Integer> dateExpenses = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(URL)) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(CREATE_TABLE_QUERY);
            }

            String GET_LAST_30_DAYS_EXPENSES = "SELECT date, SUM(amount) as total FROM expense " +
                    "WHERE date >= DATE('now', '-30 days') " +
                    "GROUP BY date " + "ORDER BY date ASC";
            PreparedStatement stmt = conn.prepareStatement(GET_LAST_30_DAYS_EXPENSES);
            ResultSet rs = stmt.executeQuery();

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (rs.next()) {
                String dateString = rs.getString("date");
                LocalDate date = LocalDate.parse(dateString, dateFormatter);
                int dateAsInt = (int) date.toEpochDay();
                dateExpenses.put(dateAsInt, rs.getInt("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dateExpenses;
    }
}
