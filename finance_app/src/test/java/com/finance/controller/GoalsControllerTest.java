package com.finance.controller;

import com.finance.dao.GoalDAO;
import com.finance.model.Goal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class GoalsControllerTest {

    private GoalDAO mockGoalDAO;
    private GoalsController goalsController;

    @BeforeEach
    void setUp() {
        mockGoalDAO = mock(GoalDAO.class);
        goalsController = new GoalsController(mockGoalDAO);
    }

    @Test
    void testAddGoal() {
        Goal goal = new Goal("Save for vacation", 500.0, 6);

        goalsController.addGoal(goal);

        verify(mockGoalDAO, times(1)).insertGoal(goal);
    }

    @Test
    void testRemoveGoal() {
        String goalName = "Save for vacation";

        goalsController.removeGoal(goalName);

        verify(mockGoalDAO, times(1)).deleteGoal(goalName);
    }

    @Test
    void testGetAllGoals() {
        List<Goal> mockGoals = Arrays.asList(
                new Goal("Vacation", 500.0, 6),
                new Goal("New Laptop", 1200.0, 12)
        );

        when(mockGoalDAO.getAllGoals()).thenReturn(mockGoals);

        List<Goal> result = goalsController.getAllGoals();

        assertEquals(2, result.size());
        assertEquals("Vacation", result.get(0).getName());
        assertEquals(1200.0, result.get(1).getSpendingLimit());
        verify(mockGoalDAO, times(1)).getAllGoals();
    }
}
