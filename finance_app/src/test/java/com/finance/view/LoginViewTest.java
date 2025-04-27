package com.finance.view;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;  // <-- ADD THIS!

public class LoginViewTest extends BaseTest {
    LoginView loginView;
    JPasswordField passwordField;
    JTextField emailField;
    JButton loginButton;

    @BeforeEach
    public void headlessInitialize() {
        if (GraphicsEnvironment.isHeadless()) {
            Assumptions.assumeTrue(false, "Skipping GUI test due to headless environment");
        }
    }

    @BeforeEach
    public void setUp() {
        loginView = spy(new LoginView());

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
