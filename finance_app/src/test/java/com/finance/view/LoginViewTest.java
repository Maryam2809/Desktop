package com.finance.view;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginViewTest extends BaseTest{
    LoginView loginView;

    @BeforeAll
    static void checkHeadless() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment detected, skipping tests");
        }
    }

    @BeforeEach
    public void setUp() {
        loginView = new LoginView();
    }

    @Test
    void testComponentsInitialization() {
        assertNotNull(loginView.getEmailField());
        assertNotNull(loginView.getPasswordField());
        assertNotNull(loginView.getLoginButton());
    }

    @Test
    public void testTitle() {
        String title = loginView.getTitle();
        assertEquals("Login", title);
    }

    @Test
    public void testLoginButton() {
        JButton loginButton = loginView.getLoginButton();
        assertEquals("Login", loginButton.getText());
    }

    @Test
    public void testEmailField(){
        JTextField emailField= loginView.getEmailField();
        assertEquals(20, emailField.getColumns());
    }

    @Test
    public void testPasswordField() {
        JPasswordField passwordField = loginView.getPasswordField();
        assertEquals(20, passwordField.getColumns());
    }

    @Test
    public void testLoginButtonActionListener() {
        JButton loginButton = loginView.getLoginButton();
        ActionListener[] listeners = loginButton.getActionListeners();
        assertEquals(1, listeners.length);
    }
}