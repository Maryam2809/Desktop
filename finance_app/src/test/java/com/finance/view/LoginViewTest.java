package com.finance.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class LoginViewTest extends BaseTest{
    LoginView loginView;

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

}