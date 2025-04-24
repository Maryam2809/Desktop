package com.finance.dao;

import com.finance.config.DatabaseConfig;
import com.finance.model.User;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDAOTest {

    private UserDAO userDAO;
    private Connection conn;

    @BeforeAll
    public void setup() {
        DatabaseConfig.createTables();
        userDAO = new UserDAO();
    }

    @BeforeEach
    public void openConnection() {
        conn = DatabaseConfig.getConnection();
    }

    @AfterEach
    public void clearUsers() throws Exception {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM users")) {
            stmt.executeUpdate();
        }
        conn.close();
    }

    @Test
    public void testSaveUser() {
        User user = new User("Alice", "Walker", "alice@example.com", "securepass");
        boolean result = userDAO.saveUser(user);
        assertTrue(result);
    }

    @Test
    public void testGetUserByEmail() {
        User user = new User("Bob", "Johnson", "bob@example.com", "bobspassword");
        userDAO.saveUser(user);

        User fetched = userDAO.getUserByEmail("bob@example.com");
        assertNotNull(fetched);
        assertEquals("Bob", fetched.getFirstName());
        assertEquals("Johnson", fetched.getLastName());
        assertEquals("bob@example.com", fetched.getEmail());
        assertEquals("bobspassword", fetched.getPassword());
    }

    @Test
    public void testGetUserByEmailNotFound() {
        User user = userDAO.getUserByEmail("nonexistent@example.com");
        assertNull(user);
    }
}