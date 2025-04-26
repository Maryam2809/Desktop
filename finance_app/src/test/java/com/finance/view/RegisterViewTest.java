package com.finance.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterViewTest extends BaseTest{
    private RegisterView registerView;

    @BeforeEach
    public void setup() {
        registerView = new RegisterView();
    }

    @Test
    public void testRegisterViewCreation() {
        assertNotNull(registerView, "RegisterView should be created successfully");
        assertEquals("Register", registerView.getTitle(), "Register View should have the correct title");
        assertEquals(400, registerView.getWidth(), "Width should be 1300");
        assertEquals(300, registerView.getHeight(), "Height should be 500");
        assertTrue(registerView.isVisible(), "RegisterView should be visible");
        assertTrue(registerView.isFocusable(), "RegisterView should be focusable");
    }

    @Test
    public void testUIComponentsInitialization() {
        assertNotNull(registerView);
        assertNotNull(registerView.getFirstNameField());
        assertNotNull(registerView.getLastNameField());
        assertNotNull(registerView.getEmailField());
        assertNotNull(registerView.getPasswordField());
        assertNotNull(registerView.getRegisterButton());
    }


    @Test
    public void testRegisterButtonActionListener() {
        ActionListener[] listeners = registerView.getRegisterButton().getActionListeners();
        assertEquals(1, listeners.length, "Register button should have one action listener");
    }

}
