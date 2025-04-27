package com.finance.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.event.ActionListener;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterViewTest extends BaseTest {

    /*private RegisterView registerView;

    @BeforeEach
    public void setup() {
        registerView = new RegisterView();
        registerView.initializeUI();
    }

    @Test
    public void testRegisterViewCreation() {
        assertNotNull(registerView, "RegisterView should be created successfully");
        assertEquals("Register", registerView.getTitle(), "Register View should have the correct title");
        assertEquals(400, registerView.getWidth(), "Width should be 400");
        assertEquals(300, registerView.getHeight(), "Height should be 300");
    }

    @Test
    public void testUIComponentsInitialization() {
        assertNotNull(registerView.getFirstNameField(), "First name field should be initialized");
        assertNotNull(registerView.getLastNameField(), "Last name field should be initialized");
        assertNotNull(registerView.getEmailField(), "Email field should be initialized");
        assertNotNull(registerView.getPasswordField(), "Password field should be initialized");
        assertNotNull(registerView.getRegisterButton(), "Register button should be initialized");
    }

    @Test
    public void testRegisterButtonActionListener() {
        ActionListener[] listeners = registerView.getRegisterButton().getActionListeners();
        assertEquals(0, listeners.length, "Register button should have no action listener by default");

        registerView.addRegisterButtonActionListener(e -> System.out.println("Button clicked"));
        listeners = registerView.getRegisterButton().getActionListeners();
        assertEquals(1, listeners.length, "Register button should have one action listener after adding one");
    }
}*/
}
