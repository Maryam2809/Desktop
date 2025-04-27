package com.finance.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterViewTest {

    private RegisterView registerView;
    private JButton registerButton;

    @BeforeEach
    public void setup() {
        registerView = new RegisterView();
        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Register button clicked");
            }
        });
        registerView.setRegisterButton(registerButton);
    }

    @Test
    public void testRegisterViewCreation() {
        assertNotNull(registerView, "RegisterView should be created successfully");
        assertEquals("Register", registerView.getTitle(), "Register View should have the correct title");
        assertEquals(400, registerView.getWidth(), "Width should be 400");
        assertEquals(300, registerView.getHeight(), "Height should be 300");
        assertTrue(registerView.isFocusable(), "RegisterView should be focusable");
    }

    @Test
    public void testRegisterButtonActionListener() {
        ActionListener[] listeners = registerButton.getActionListeners();
        assertNotNull(listeners, "Listeners array should not be null");
        assertEquals(1, listeners.length, "Register button should have one action listener");
        listeners[0].actionPerformed(new ActionEvent(registerButton, ActionEvent.ACTION_PERFORMED, "command"));
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
}
