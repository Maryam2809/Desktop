package org.example;

import java.time.LocalDate;
import java.util.Map;

public class InputController {

    public ExpenseDAO DAO;
    public DashboardPage dashboard;

    public InputController(ExpenseDAO DAO){
        this.DAO= DAO;
    }

    public void addexpense(String expenseNameEntered, String expenseAmountEntered, String expenseCategoryEntered, String expenseDateEntered){
        int Amount = Integer.parseInt(expenseAmountEntered);
        LocalDate date = LocalDate.parse(expenseDateEntered);
        Expense newexpense = new Expense(expenseNameEntered, Amount, expenseCategoryEntered, date);
        DAO.saveExpense(newexpense);

    }

    public Map<String, Integer> getExpensesByCategory(){
        return DAO.getExpensesByCategory();
    }

    public Map <Integer, Integer> getMonthsExpenses(){
        return DAO.getMonthsExpenses();
    }

}