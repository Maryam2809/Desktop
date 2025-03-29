package com.finance.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.finance.dao.FinanceDAO;
import com.finance.model.Expense;

public class InputPageView extends JPanel {
    private JTextField descriptionField, amountField, categoryField;
    private JButton addButton, removeButton;
    private JTextArea expenseListArea;
    private FinanceDAO financeDAO;

    public InputPageView() {
        setLayout(new BorderLayout());

        financeDAO = new FinanceDAO();
        descriptionField = new JTextField(20);
        amountField = new JTextField(20);
        categoryField = new JTextField(20);
        addButton = new JButton("Add Expense");
        removeButton = new JButton("Remove Expense");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        expenseListArea = new JTextArea();
        expenseListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(expenseListArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeExpense();
            }
        });

        updateExpenseList();
    }

    private void addExpense() {
        String description = descriptionField.getText();
        double amount = Double.parseDouble(amountField.getText());
        String category = categoryField.getText();

        Expense expense = new Expense(description, amount, category);
        financeDAO.addExpense(expense);

        updateExpenseList();
        descriptionField.setText("");
        amountField.setText("");
        categoryField.setText("");
    }

    private void removeExpense() {
        String[] expenses = expenseListArea.getText().split("\n");
        if (expenses.length > 0) {
            int expenseId = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the expense to remove:"));
            financeDAO.removeExpense(expenseId);
            updateExpenseList();
        }
    }

    private void updateExpenseList() {
        StringBuilder list = new StringBuilder();
        for (Expense expense : financeDAO.getAllExpenses()) {
            list.append("ID: ").append(expense.getId()).append(", ")
                    .append("Description: ").append(expense.getDescription()).append(", ")
                    .append("Amount: ").append(expense.getAmount()).append(", ")
                    .append("Category: ").append(expense.getCategory()).append("\n");
        }
        expenseListArea.setText(list.toString());
    }
}