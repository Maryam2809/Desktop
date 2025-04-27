package com.finance.view;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

public class LoginViewTest extends BaseTest {

    LoginView loginView;

    @BeforeAll
    static void checkHeadless() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment detected, skipping GUI rendering.");
        }
    }

    @BeforeEach
    public void setUp() {
        loginView = new LoginView();
    }

    @Test
    void testComponentsInitialization() {
        assertNotNull(loginView.getEmailField(), "Email field should be initialized");
        assertNotNull(loginView.getPasswordField(), "Password field should be initialized");
        assertNotNull(loginView.getLoginButton(), "Login button should be initialized");
    }

    @Test
    public void testTitle() {
        String title = loginView.getTitle();
        assertEquals("Login", title, "Login view should have correct title");
    }

    @Test
    public void testLoginButton() {
        JButton loginButton = loginView.getLoginButton();
        assertEquals("Login", loginButton.getText(), "Login button should have text 'Login'");
    }

    @Test
    public void testEmailField() {
        JTextField emailField = loginView.getEmailField();
        assertEquals(20, emailField.getColumns(), "Email field should have 20 columns");
    }

    @Test
    public void testPasswordField() {
        JPasswordField passwordField = loginView.getPasswordField();
        assertEquals(20, passwordField.getColumns(), "Password field should have 20 columns");
    }

    @Test
    public void testLoginButtonActionListener() {
        JButton loginButton = loginView.getLoginButton();
        ActionListener[] listeners = loginButton.getActionListeners();
        assertEquals(1, listeners.length, "There should be one action listener for the login button");

        ActionEvent event = new ActionEvent(loginButton, ActionEvent.ACTION_PERFORMED, "Login");
        listeners[0].actionPerformed(event);

        System.out.println("Login button action performed");
    }
}
