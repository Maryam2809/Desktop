package org.example;

import javax.swing.*;
import java.awt.*;


public class InputPage extends JPanel {

    private JTextField expenseName = new JTextField(20);
    private JTextField expenseAmount = new JTextField(20);
    private JComboBox<String> expenseCategory = new JComboBox<>(new String[]{"Food", "Transport", "Utilities", "Entertainment"});
    private JTextField expenseDate = new JTextField(20);
    private InputController controller;

    public InputPage(InputController controller) {
        this.controller = controller;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Transaction Name:"), gbc);
        gbc.gridx = 1;
        add(expenseName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Transaction Amount:"), gbc);
        gbc.gridx = 1;
        add(expenseAmount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Transaction Category:"), gbc);
        gbc.gridx = 1;
        add(expenseCategory, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Transaction Date:"), gbc);
        gbc.gridx = 1;
        add(expenseDate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> handleExpense());
        add(submitButton, gbc);

        //displayExpense();
    }

    private void handleExpense() {
        String expenseNameEntered = expenseName.getText();
        String expenseAmountEntered = expenseAmount.getText();
        String expenseCategoryEntered = (String) expenseCategory.getSelectedItem();
        String expenseDateEntered = expenseDate.getText();

        controller.addexpense(expenseNameEntered, expenseAmountEntered, expenseCategoryEntered, expenseDateEntered);
    }

    public void displayExpense() {
    }
}

