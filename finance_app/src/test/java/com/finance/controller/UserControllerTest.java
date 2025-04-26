package com.finance.controller;

import com.finance.dao.UserDAO;
import com.finance.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    private UserDAO userDAO;
    private UserController userController;

    @BeforeEach
    void setUp() {
        userDAO = mock(UserDAO.class);
        userController = new UserController(userDAO);
    }

    @Test
    void testSuccessfulLogin() {
        User mockUser = new User("John", "Doe", "john@example.com", "password123");
        when(userDAO.getUserByEmail("john@example.com")).thenReturn(mockUser);

        User result = userController.loginUser("john@example.com", "password123");

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

    @Test
    void testLoginWrongPassword() {
        User mockUser = new User("John", "Doe", "john@example.com", "password123");
        when(userDAO.getUserByEmail("john@example.com")).thenReturn(mockUser);

        User result = userController.loginUser("john@example.com", "wrongpassword");

        assertNull(result);
    }

    @Test
    void testLoginUserNotFound() {
        when(userDAO.getUserByEmail("nonexistent@example.com")).thenReturn(null);

        User result = userController.loginUser("nonexistent@example.com", "password");

        assertNull(result);
    }

    @Test
    void testLoginWithNullEmail() {
        User result = userController.loginUser(null, "password");

        assertNull(result);
        verify(userDAO, times(1)).getUserByEmail(null);
    }

    @Test
    void testLoginWithNullPassword() {
        User mockUser = new User("John", "Doe", "john@example.com", "password123");
        when(userDAO.getUserByEmail("john@example.com")).thenReturn(mockUser);

        User result = userController.loginUser("john@example.com", null);

        assertNull(result);
    }

    @Test
    void testLoginWithEmptyEmailAndPassword() {
        User result = userController.loginUser("", "");

        assertNull(result);
        verify(userDAO, times(1)).getUserByEmail("");
    }

    @Test
    void testSuccessfulRegistration() {
        User newUser = new User("Jane", "Doe", "jane@example.com", "securePass!");
        when(userDAO.saveUser(newUser)).thenReturn(true);

        boolean result = userController.registerUser(newUser);

        assertTrue(result);
        verify(userDAO, times(1)).saveUser(newUser);
    }

    @Test
    void testFailedRegistration() {
        User newUser = new User("Jane", "Doe", "jane@example.com", "securePass!");
        when(userDAO.saveUser(newUser)).thenReturn(false);

        boolean result = userController.registerUser(newUser);

        assertFalse(result);
        verify(userDAO, times(1)).saveUser(newUser);
    }

    @Test
    void testRegisterNullUser() {
        boolean result = userController.registerUser(null);

        assertFalse(result);
        verify(userDAO, times(1)).saveUser(null);
    }
}
