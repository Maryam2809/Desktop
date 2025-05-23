package com.finance.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {
    private static final String URL = "jdbc:sqlite:finance.db";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static void createTables() {
        String usersTable = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "first_name TEXT NOT NULL, "
                + "last_name TEXT NOT NULL, "
                + "email TEXT UNIQUE NOT NULL, "
                + "password TEXT NOT NULL);";

        String expensesTable = "CREATE TABLE IF NOT EXISTS expenses ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "user_id INTEGER NOT NULL, "
                + "description TEXT NOT NULL, "
                + "amount REAL NOT NULL, "
                + "category TEXT NOT NULL, "
                + "date TEXT NOT NULL, "
                + "type TEXT NOT NULL, "
                + "FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE);";

        String goalsTable = "CREATE TABLE IF NOT EXISTS goals ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT UNIQUE NOT NULL, "
                + "spending_limit REAL NOT NULL, "
                + "duration INTEGER NOT NULL);";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(usersTable);
            stmt.execute(expensesTable);
            stmt.execute(goalsTable);
            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}