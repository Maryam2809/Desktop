package com.finance.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {

    @Test
    public void testExpenseConstructorAndGetters() {
        Expense expense = new Expense("Groceries", 75.5, "Food", "2025-04-01", "debit");

        assertEquals("Groceries", expense.getDescription());
        assertEquals(75.5, expense.getAmount());
        assertEquals("Food", expense.getCategory());
        assertEquals("2025-04-01", expense.getDate());
        assertEquals("debit", expense.getType());
    }

    @Test
    public void testSettersAndGetters() {
        Expense expense = new Expense("", 0, "", "", "");

        expense.setId(10);
        expense.setDescription("Rent");
        expense.setAmount(950.0);
        expense.setCategory("Housing");
        expense.setDate("2025-04-15");
        expense.setType("credit");

        assertEquals(10, expense.getId());
        assertEquals("Rent", expense.getDescription());
        assertEquals(950.0, expense.getAmount());
        assertEquals("Housing", expense.getCategory());
        assertEquals("2025-04-15", expense.getDate());
        assertEquals("credit", expense.getType());
    }
}