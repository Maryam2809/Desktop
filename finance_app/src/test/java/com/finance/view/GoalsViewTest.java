package com.finance.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.*;
import java.awt.event.ActionListener;
import static org.junit.jupiter.api.Assertions.*;

public class GoalsViewTest extends BaseTest {
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
    public void testAddButtonActionListener() {
        JButton addButton = goalsView.getAddGoalButton();
        ActionListener[] listeners = addButton.getActionListeners();
        assertEquals(1, listeners.length, "There should be one listener for the Add Goal button");
    }

    @Test
    public void testRemoveButtonActionListener() {
        JButton removeButton = goalsView.getRemoveGoalButton();
        ActionListener[] listeners = removeButton.getActionListeners();
        assertEquals(1, listeners.length, "There should be one listener for the Remove Goal button");
    }
}
