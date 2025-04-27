package com.finance.view;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.lang.reflect.Field;

import com.finance.controller.UserController;
import com.finance.model.User;

import javax.swing.*;

class LoginViewTest {

    @Mock
    private UserController mockUserController;
    private LoginView loginView;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);

        loginView = new LoginView();

        Field userControllerField = LoginView.class.getDeclaredField("userController");
        userControllerField.setAccessible(true);
        userControllerField.set(loginView, mockUserController);
    }

    @Test
    void testLogin_SuccessfulLogin() {
        String email = "test@example.com";
        String password = "password123";
        User mockUser = new User("john","Doe","email","password");
        when(mockUserController.loginUser(email, password)).thenReturn(mockUser);

        loginView.getEmailField().setText(email);
        loginView.getPasswordField().setText(password);

        JButton loginButton = loginView.getLoginButton();
        loginButton.doClick();

        verify(mockUserController).loginUser(eq(email), eq(password));

    }

    @Test
    void testLogin_FailedLogin() {
        String email = "test@example.com";
        String password = "wrongPassword";
        when(mockUserController.loginUser(email, password)).thenReturn(null);

        loginView.getEmailField().setText(email);
        loginView.getPasswordField().setText(password);

        JButton loginButton = loginView.getLoginButton();
        loginButton.doClick();

        verify(mockUserController).loginUser(eq(email), eq(password));
    }
}
