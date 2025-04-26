package com.finance.controller;

import com.finance.dao.GoalDAO;
import com.finance.model.Goal;

import java.util.List;

public class GoalsController {
    private GoalDAO goalDAO;

    // Default constructor (for production use)
    public GoalsController() {
        this.goalDAO = new GoalDAO();
    }

    // New constructor (for testing/mocking)
    public GoalsController(GoalDAO goalDAO) {
        this.goalDAO = goalDAO;
    }

    public void addGoal(Goal goal) {
        goalDAO.insertGoal(goal);
    }

    public void removeGoal(String goalName) {
        goalDAO.deleteGoal(goalName);
    }

    public List<Goal> getAllGoals() {
        return goalDAO.getAllGoals();
    }
}
