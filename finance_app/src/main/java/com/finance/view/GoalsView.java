package com.finance.view;

import com.finance.controller.GoalsController;
import com.finance.model.Goal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GoalsView extends JPanel {
    private GoalsController goalsController;
    private DefaultListModel<String> goalsListModel;
    private JList<String> goalsList;
    private JTextField goalNameField, spendingLimitField, durationField;

    public GoalsView() {
        goalsController = new GoalsController();

        setLayout(new BorderLayout());
        JLabel title = new JLabel("Manage Goals", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        goalsListModel = new DefaultListModel<>();
        goalsList = new JList<>(goalsListModel);
        JScrollPane scrollPane = new JScrollPane(goalsList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        inputPanel.add(new JLabel("Goal Name:"));
        goalNameField = new JTextField();
        inputPanel.add(goalNameField);

        inputPanel.add(new JLabel("Spending Limit:"));
        spendingLimitField = new JTextField();
        inputPanel.add(spendingLimitField);

        inputPanel.add(new JLabel("Duration (weeks):"));
        durationField = new JTextField();
        inputPanel.add(durationField);

        JButton addGoalButton = new JButton("Add Goal");
        JButton removeGoalButton = new JButton("Remove Selected Goal");

        inputPanel.add(addGoalButton);
        inputPanel.add(removeGoalButton);

        add(inputPanel, BorderLayout.SOUTH);

        refreshGoalsList();

        addGoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGoal();
            }
        });

        removeGoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeGoal();
            }
        });
    }

    private void refreshGoalsList() {
        goalsListModel.clear();
        List<Goal> goals = goalsController.getAllGoals();
        for (Goal goal : goals) {
            goalsListModel.addElement(goal.getName() + " - Limit: £" + goal.getSpendingLimit() + " (" + goal.getDuration() + " weeks)");
        }
    }

    private void addGoal() {
        String name = goalNameField.getText();
        String limitText = spendingLimitField.getText();
        String durationText = durationField.getText();

        if (name.isEmpty() || limitText.isEmpty() || durationText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double limit = Double.parseDouble(limitText);
            int duration = Integer.parseInt(durationText);
            goalsController.addGoal(new Goal(name, limit, duration));
            refreshGoalsList();
            JOptionPane.showMessageDialog(this, "Goal added successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeGoal() {
        int selectedIndex = goalsList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedGoal = goalsListModel.getElementAt(selectedIndex);
            String goalName = selectedGoal.split(" - ")[0];

            goalsController.removeGoal(goalName);
            refreshGoalsList();
            JOptionPane.showMessageDialog(this, "Goal removed successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Select a goal to remove", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}