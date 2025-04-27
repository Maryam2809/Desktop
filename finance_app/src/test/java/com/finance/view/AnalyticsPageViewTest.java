package com.finance.view;

import com.finance.model.Expense;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnalyticsPageViewTest extends BaseTest{
    private AnalyticsPageView view;

    @BeforeAll
    static void checkHeadless() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment detected, skipping tests");
        }
    }

    @BeforeEach
    public void setUp() {
        view = new AnalyticsPageView();
    }

    @Test
    void testAnalyticsPageViewCreation() {
        assertNotNull(view, "AnalyticsPageView should be created successfully");
        assertTrue(view.isVisible(), "AnalyticsPageView should be visible");
        assertTrue(view.isFocusable(), "AnalyticsPageView should be focusable");
    }

    @Test
    void testComponentsInitialization() {
        AnalyticsPageView view = new AnalyticsPageView();

        assertNotNull(view.getMonthlyCategoryTotals());
        assertNotNull(view.getWeeklyCategoryTotals());
        assertNotNull(view.getMonthlyCategoryTotalsLabel());
        assertNotNull(view.getWeeklyCategoryTotalsLabel());
        assertNotNull(view.getRecentExpenseLabel());
    }

    @Test
    void testGetRecentExpenseText() {
        AnalyticsPageView view = new AnalyticsPageView();
        List<Expense> mockExpenses = Arrays.asList(
                new Expense("Lunch", 10.50, "Food", "2025-04-20", "Expense"),
                new Expense("Shirt", 25.00, "Clothes", "2025-04-21", "Expense")
        );

        view.getRecentExpenseText(mockExpenses);
        String summary = view.getExpenseSummary();

        assertNotNull(summary);
        assertTrue(summary.contains("2025-04-20"));
        assertTrue(summary.contains("2025-04-21"));
        assertTrue(summary.contains("£10.5"));
        assertTrue(summary.contains("£25.0"));
        assertTrue(summary.contains("(Food)"));
        assertTrue(summary.contains("(Clothes)"));
    }
}
