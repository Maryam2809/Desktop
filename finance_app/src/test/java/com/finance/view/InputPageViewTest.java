package com.finance.view;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.event.ActionListener;

public class InputPageViewTest extends BaseTest{
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
    public void testComponentsInitialization() {
        assertNotNull(inputPageView.getDescriptionField());
        assertNotNull(inputPageView.getAmountField());
        assertInstanceOf(JTextField.class, inputPageView.getDateField());
        assertInstanceOf(JComboBox.class, inputPageView.getCategoryDropdown());
        assertInstanceOf(JComboBox.class, inputPageView.getTypeDropdown());
        assertInstanceOf(JButton.class, inputPageView.getAddButton());
        assertInstanceOf(JButton.class, inputPageView.getRemoveButton());
    }

    @Test
    public void testLayout() {
        assertNotNull(inputPageView.getLayout(), "Layout should not be null");
    }

    @Test
    public void testAddButtonActionListener() {
        JButton addButton = inputPageView.getAddButton();
        ActionListener[] listeners = addButton.getActionListeners();
        assertEquals(1, listeners.length);
    }

    @Test
    public void testRemoveButtonActionListener() {
        JButton removeButton = inputPageView.getRemoveButton();
        ActionListener[] listeners = removeButton.getActionListeners();
        assertEquals(1, listeners.length);
    }

}