package com.finance.controller;

import com.finance.dao.GoalDAO;
import com.finance.model.Goal;

import java.util.List;

public class GoalsController {
    private GoalDAO goalDAO;

    public GoalsController() {
        goalDAO = new GoalDAO();
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