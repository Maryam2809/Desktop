package com.finance.view;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

public class GoalsViewTest extends BaseTest{
    private GoalsView goalsView;

    @BeforeAll
    static void checkHeadless() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment detected, skipping tests");
        }
    }

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
         GoalsView goalsView = new GoalsView();
         assertNotNull(goalsView);
         assertNotNull(goalsView.getGoalInputField());
         assertNotNull(goalsView.getAddGoalButton());
         assertNotNull(goalsView.getRemoveGoalButton());
         assertNotNull(goalsView.getGoalList());
     }

    @Test
    public void testLayout() {
        GoalsView goalsView = new GoalsView();
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
        GoalsView goalsView = new GoalsView();
        JButton addButton = goalsView.getAddGoalButton();
        ActionListener[] listeners = addButton.getActionListeners();
        assertEquals(1, listeners.length);
    }

    @Test
    public void testRemoveButtonActionListener() {
        GoalsView goalsView = new GoalsView();
        JButton removeButton = goalsView.getRemoveGoalButton();
        ActionListener[] listeners = removeButton.getActionListeners();
        assertEquals(1, listeners.length);
    }
}
