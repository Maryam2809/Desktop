package com.finance.config;

import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConfigTest {

    private Connection connection;
    static class TestDatabaseConfig extends DatabaseConfig {
        private static Connection testConnection;

        public static void setConnection(Connection conn) {
            testConnection = conn;
        }

        public static void createTestTables() {
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
                    + "FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE);";

            String goalsTable = "CREATE TABLE IF NOT EXISTS goals ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "name TEXT UNIQUE NOT NULL, "
                    + "spending_limit REAL NOT NULL, "
                    + "duration INTEGER NOT NULL);";

            try (Statement stmt = testConnection.createStatement()) {
                stmt.execute(usersTable);
                stmt.execute(expensesTable);
                stmt.execute(goalsTable);
            } catch (SQLException e) {
                throw new RuntimeException("Failed to create test tables", e);
            }
        }
    }

    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");
        TestDatabaseConfig.setConnection(connection);
        TestDatabaseConfig.createTestTables();
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void testUsersTableExists() throws SQLException {
        assertTrue(tableExists("users"), "users table should exist");
    }

    @Test
    void testExpensesTableExists() throws SQLException {
        assertTrue(tableExists("expenses"), "expenses table should exist");
    }

    @Test
    void testGoalsTableExists() throws SQLException {
        assertTrue(tableExists("goals"), "goals table should exist");
    }

    private boolean tableExists(String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet rs = metaData.getTables(null, null, tableName, null)) {
            return rs.next();
        }
    }
}