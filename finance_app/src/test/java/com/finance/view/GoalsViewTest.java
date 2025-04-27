package com.finance.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

public class GoalsViewTest {

    private GoalsView goalsView;

    @BeforeEach
    public void setUp() {
        goalsView = new GoalsView();
    }

    @Test
    public void testGoalsViewCreation() {
        assertNotNull(goalsView, "GoalsView should be created successfully");
        assertTrue(goalsView.isVisible(), "GoalsView should be visible");
        assertTrue(goalsView.isFocusable(), "GoalsView should be focusable");
    }

    @Test
    public void testGoalsViewInitialization() {
        assertNotNull(goalsView.getGoalInputField(), "Goal input field should be initialized");
        assertNotNull(goalsView.getAddGoalButton(), "Add goal button should be initialized");
        assertNotNull(goalsView.getRemoveGoalButton(), "Remove goal button should be initialized");
        assertNotNull(goalsView.getGoalList(), "Goal list should be initialized");
    }

    @Test
    public void testLayout() {
        assertInstanceOf(BorderLayout.class, goalsView.getLayout(), "Layout should be BorderLayout");

        BorderLayout layout = (BorderLayout) goalsView.getLayout();

        Component north = layout.getLayoutComponent(BorderLayout.NORTH);
        Component center = layout.getLayoutComponent(BorderLayout.CENTER);
        Component south = layout.getLayoutComponent(BorderLayout.SOUTH);

        assertNotNull(north, "Title label (NORTH) should not be null");
        assertNotNull(center, "Goals list (CENTER) should not be null");
        assertNotNull(south, "Input panel (SOUTH) should not be null");


        assertInstanceOf(JLabel.class, north, "North component should be a JLabel (Title)");
        assertInstanceOf(JScrollPane.class, center, "Center component should be a JScrollPane (for goals list)");
        assertInstanceOf(JPanel.class, south, "South component should be a JPanel (for inputs and buttons)");
    }

    @Test
    public void testAddButtonActionListener() {
        JButton addButton = goalsView.getAddGoalButton();
        ActionListener[] listeners = addButton.getActionListeners();
        assertEquals(1, listeners.length, "Add Goal button should have exactly one action listener");
    }

    @Test
    public void testRemoveButtonActionListener() {
        JButton removeButton = goalsView.getRemoveGoalButton();
        ActionListener[] listeners = removeButton.getActionListeners();
        assertEquals(1, listeners.length, "Remove Goal button should have exactly one action listener");
    }

    @Test
    public void testAddGoalIncompleteFields() {
        goalsView.getGoalInputField().setText("");
        goalsView.getAddGoalButton().doClick();

    }

    @Test
    public void testAddGoalInvalidNumber() {
        goalsView.getGoalInputField().setText("New Goal");
        goalsView.getAddGoalButton().doClick();

    }


    @Test
    public void testRemoveGoal() {
        String goalName = "Save for vacation";
        DefaultListModel<String> model = (DefaultListModel<String>) goalsView.getGoalList().getModel();
        model.addElement(goalName);


        goalsView.getGoalList().setSelectedIndex(0);
        goalsView.getRemoveGoalButton().doClick();

        assertFalse(model.contains(goalName), "The removed goal should no longer be in the list");
    }
}
