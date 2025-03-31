package com.finance;

import com.finance.config.DatabaseConfig;
import com.finance.dao.FinanceDAO;
import com.finance.dao.UserDAO;
import com.finance.model.Expense;
import com.finance.model.User;
import com.finance.util.PasswordUtil;

import java.util.List;

public class FinanceApplication {

    public static void main(String[] args) {
        DatabaseConfig.createTables();

        User user = new User("John", "Doe", "john.doe@example.com", PasswordUtil.encryptPassword("securePassword123"));
        UserDAO userDAO = new UserDAO();
        if (userDAO.saveUser(user)) {
            System.out.println("User added successfully!");
        } else {
            System.out.println("Error adding user.");
        }

        Expense expense = new Expense("Lunch", 15.50, "Food");
        FinanceDAO financeDAO = new FinanceDAO();
        financeDAO.addExpense(expense);
        System.out.println("Expense added successfully!");

        List<Expense> expenses = financeDAO.getAllExpenses();
        System.out.println("All Expenses:");
        for (Expense exp : expenses) {
            System.out.println("ID: " + exp.getId() + ", Description: " + exp.getDescription() +
                    ", Amount: " + exp.getAmount() + ", Category: " + exp.getCategory());
        }
        
        if (!expenses.isEmpty()) {
            int expenseIdToRemove = expenses.get(0).getId();
            financeDAO.removeExpense(expenseIdToRemove);
            System.out.println("Expense with ID " + expenseIdToRemove + " removed.");
        }
    }
}