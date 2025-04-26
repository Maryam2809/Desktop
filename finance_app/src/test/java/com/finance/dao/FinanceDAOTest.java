package com.finance.dao;

import com.finance.config.DatabaseConfig;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FinanceDAOTest {

    private Connection conn;

    @BeforeAll
    public void setupDatabase() {
        DatabaseConfig.createTables();
    }

    @BeforeEach
    public void connect() {
        conn = DatabaseConfig.getConnection();
    }

    @AfterEach
    public void cleanUp() throws Exception {
        try (PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM expenses");
             PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM users");
             PreparedStatement stmt3 = conn.prepareStatement("DELETE FROM goals")) {
            stmt1.executeUpdate();
            stmt2.executeUpdate();
            stmt3.executeUpdate();
        }
        conn.close();
    }

    @Test
    public void testUserInsertAndQuery() throws Exception {
        String insertSql = "INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
            stmt.setString(1, "Alice");
            stmt.setString(2, "Wright");
            stmt.setString(3, "alice@example.com");
            stmt.setString(4, "password123");
            int rowsInserted = stmt.executeUpdate();
            assertEquals(1, rowsInserted);
        }

        String querySql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(querySql)) {
            stmt.setString(1, "alice@example.com");
            ResultSet rs = stmt.executeQuery();
            assertTrue(rs.next());
            assertEquals("Alice", rs.getString("first_name"));
        }
    }

}