package com.finance.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class InputPageViewTest extends BaseTest {
    private InputPageView inputPageView;

    @BeforeEach
    public void setUp() {
        inputPageView = new InputPageView();
    }

    @Test
    public void testInputPageViewCreation() {
        assertNotNull(inputPageView, "InputPageView should be created successfully");
        assertTrue(inputPageView.isVisible(), "InputPageView should be visible");
        assertTrue(inputPageView.isFocusable(), "InputPageView should be focusable");
    }

    @Test
    public void testAddButtonActionListener() {
        JButton addButton = inputPageView.getAddButton();
        ActionListener[] listeners = addButton.getActionListeners();

        assertNotNull(listeners, "Listeners should not be null");
        assertTrue(listeners.length > 0, "At least one listener should be attached to the Add Button");
    }

    @Test
    public void testRemoveButtonActionListener() {
        JButton removeButton = inputPageView.getRemoveButton();
        ActionListener[] listeners = removeButton.getActionListeners();

        assertNotNull(listeners, "Listeners should not be null");
        assertTrue(listeners.length > 0, "At least one listener should be attached to the Add Button");
    }
}
