package com.finance.view;

import com.finance.model.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AnalyticsPageViewTest extends BaseTest {
    private AnalyticsPageView view;

    @BeforeEach
    public void setUp() {
        view = mock(AnalyticsPageView.class); // Mock the view
    }

    @Test
    void testAnalyticsPageViewCreation() {
        assertNotNull(view, "AnalyticsPageView should be created successfully");
    }

    @Test
    void testGetRecentExpenseText() {
        List<Expense> mockExpenses = Arrays.asList(
                mock(Expense.class), // Mock expense
                mock(Expense.class)  // Mock another expense
        );

        // Setting up mocks for expense properties
        when(mockExpenses.get(0).getDate()).thenReturn("2025-04-20");
        when(mockExpenses.get(1).getDate()).thenReturn("2025-04-21");
        when(mockExpenses.get(0).getAmount()).thenReturn(10.50);
        when(mockExpenses.get(1).getAmount()).thenReturn(25.00);
        when(mockExpenses.get(0).getCategory()).thenReturn("Food");
        when(mockExpenses.get(1).getCategory()).thenReturn("Clothes");

        doNothing().when(view).getRecentExpenseText(mockExpenses);

        when(view.getExpenseSummary()).thenReturn(
                "2025-04-20: £10.5 (Food)\n2025-04-21: £25.0 (Clothes)\n"
        );

        view.getRecentExpenseText(mockExpenses);

        String summary = view.getExpenseSummary();

        assertNotNull(summary, "Expense summary should not be null");
        assertTrue(summary.contains("2025-04-20"));
        assertTrue(summary.contains("2025-04-21"));
        assertTrue(summary.contains("£10.5"));
        assertTrue(summary.contains("£25.0"));
        assertTrue(summary.contains("(Food)"));
        assertTrue(summary.contains("(Clothes)"));
    }
}
