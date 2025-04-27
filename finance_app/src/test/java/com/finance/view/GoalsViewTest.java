package com.finance.view;

import com.finance.controller.GoalsController;
import com.finance.model.Goal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GoalsViewTest {

    private GoalsView goalsView;
    private GoalsController mockGoalsController;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        mockGoalsController = mock(GoalsController.class);
        goalsView = new GoalsView();

        Field goalsControllerField = GoalsView.class.getDeclaredField("goalsController");
        goalsControllerField.setAccessible(true);
        goalsControllerField.set(goalsView, mockGoalsController);
    }

    @Test
    public void testGoalsViewCreation() {
        assertNotNull(goalsView, "GoalsView should be created successfully");
        assertTrue(goalsView.isVisible(), "GoalsView should be visible");
        assertTrue(goalsView.isFocusable(), "GoalsView should be focusable");
    }

    @Test
    public void testGoalsViewInitialization() {
        assertNotNull(goalsView.getGoalNameField(), "Goal name field should be initialized");
        assertNotNull(goalsView.getSpendingLimitField(), "Spending limit field should be initialized");
        assertNotNull(goalsView.getDurationField(), "Duration field should be initialized");
        assertNotNull(goalsView.getAddGoalButton(), "Add goal button should be initialized");
        assertNotNull(goalsView.getRemoveGoalButton(), "Remove goal button should be initialized");
        assertNotNull(goalsView.getGoalsList(), "Goal list should be initialized");
    }

}
