package com.finance.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoalTest {

    @Test
    public void testGoalConstructorAndGetters() {
        String name = "Vacation Fund";
        double spendingLimit = 1500.0;
        int duration = 6;

        Goal goal = new Goal(name, spendingLimit, duration);

        assertEquals(name, goal.getName());
        assertEquals(spendingLimit, goal.getSpendingLimit());
        assertEquals(duration, goal.getDuration());
    }
}