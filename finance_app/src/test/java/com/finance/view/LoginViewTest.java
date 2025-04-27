package com.finance.view;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;  // <-- ADD THIS!

public class LoginViewTest extends BaseTest {
    LoginView loginView;
    JPasswordField passwordField;
    JTextField emailField;
    JButton loginButton;

    @BeforeAll
    public static void setUpHeadless() {
        System.setProperty("java.awt.headless", "true");
    }

    @BeforeEach
    public void setUp() {
        // Create a spy of LoginView
        loginView = spy(new LoginView());

        // Stub out GUI-related methods
        doNothing().when(loginView).setVisible(anyBoolean());
        doNothing().when(loginView).pack();
        doNothing().when(loginView).setDefaultCloseOperation(anyInt());
    }

    @Test
    void testComponentsInitialization() {
        assertNotNull(loginView.getEmailField());
        assertNotNull(loginView.getPasswordField());
        assertNotNull(loginView.getLoginButton());
    }

    @Test
    public void testLoginButton() {
        loginButton = loginView.getLoginButton();
        assertEquals("Login", loginButton.getText());
    }

    @Test
    public void testEmailField() {
        emailField = loginView.getEmailField();
        assertEquals(20, emailField.getColumns());
    }

    @Test
    public void testPasswordField() {
        passwordField = loginView.getPasswordField();
        assertEquals(20, passwordField.getColumns());
    }
}
