package com.finance.view;

import com.finance.dao.FinanceDAO;
import com.finance.model.Expense;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class AnalyticsPageView extends JPanel {
    private final FinanceDAO financeDAO;
    private String expenseSummary;

    private JLabel recentExpenseLabel;

    public AnalyticsPageView() {
        setLayout(new BorderLayout());
        setVisible(true);

        financeDAO = new FinanceDAO();

        List<Expense> allExpenses = financeDAO.getAllExpenses();
        List<Expense> recentExpenses = financeDAO.getRecentExpenses();

        DefaultPieDataset weeklyDataset = createWeeklyDataset(allExpenses);
        DefaultPieDataset monthlyDataset = createMonthlyDataset(allExpenses);
        DefaultCategoryDataset lineDataset = createLineDataset(allExpenses);

        JPanel chartsPanel = createChartsPanel(weeklyDataset, monthlyDataset, lineDataset, recentExpenses);

        add(chartsPanel, BorderLayout.CENTER);
    }

    private DefaultPieDataset createWeeklyDataset(List<Expense> expenses) {
        Map<String, Double> weeklyCategoryTotals = new HashMap<>();
        LocalDate now = LocalDate.now();

        for (Expense expense : expenses) {
            LocalDate expenseDate = LocalDate.parse(expense.getDate(), DateTimeFormatter.ISO_DATE);
            if (expenseDate.isAfter(now.minusWeeks(1))) {
                weeklyCategoryTotals.merge(expense.getCategory(), expense.getAmount(), Double::sum);
            }
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        weeklyCategoryTotals.forEach(dataset::setValue);
        return dataset;
    }

    private DefaultPieDataset createMonthlyDataset(List<Expense> expenses) {
        Map<String, Double> monthlyCategoryTotals = new HashMap<>();
        LocalDate now = LocalDate.now();

        for (Expense expense : expenses) {
            LocalDate expenseDate = LocalDate.parse(expense.getDate(), DateTimeFormatter.ISO_DATE);
            if (expenseDate.getMonth() == now.getMonth() && expenseDate.getYear() == now.getYear()) {
                monthlyCategoryTotals.merge(expense.getCategory(), expense.getAmount(), Double::sum);
            }
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        monthlyCategoryTotals.forEach(dataset::setValue);
        return dataset;
    }

    private DefaultCategoryDataset createLineDataset(List<Expense> expenses) {
        Map<String, Double> dailyTotals = new TreeMap<>();

        for (Expense expense : expenses) {
            dailyTotals.merge(expense.getDate(), expense.getAmount(), Double::sum);
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dailyTotals.forEach((date, amount) -> dataset.addValue(amount, "Expenses", date));
        return dataset;
    }

    private JPanel createChartsPanel(DefaultPieDataset weeklyDataset, DefaultPieDataset monthlyDataset,
                                     DefaultCategoryDataset lineDataset, List<Expense> recentExpenses) {

        ChartPanel weeklyPanel = new ChartPanel(createPieChart("Weekly Expense Breakdown", weeklyDataset));
        ChartPanel monthlyPanel = new ChartPanel(createPieChart("Monthly Expense Breakdown", monthlyDataset));
        ChartPanel linePanel = new ChartPanel(createLineChart("Weekly Expenses", "Date", "Amount (£)", lineDataset));
        JPanel recentPanel = createRecentTransactionsPanel(recentExpenses);

        // Remove monthly and weekly totals labels since we are focusing on recent expenses
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.add(weeklyPanel);
        panel.add(monthlyPanel);
        panel.add(linePanel);
        panel.add(recentPanel);

        return panel;
    }

    private JFreeChart createPieChart(String title, DefaultPieDataset dataset) {
        return ChartFactory.createPieChart(title, dataset, true, true, false);
    }

    private JFreeChart createLineChart(String title, String categoryAxisLabel, String valueAxisLabel, DefaultCategoryDataset dataset) {
        return ChartFactory.createLineChart(title, categoryAxisLabel, valueAxisLabel, dataset);
    }

    private JPanel createRecentTransactionsPanel(List<Expense> recentExpenses) {
        getRecentExpenseText(recentExpenses);

        // Create heading for recent expenses
        recentExpenseLabel = new JLabel("<html><h3>Recent Expenses</h3><br>" + expenseSummary.replace("\n", "<br>") + "</html>");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(recentExpenseLabel), BorderLayout.CENTER);
        return panel;
    }

    public void getRecentExpenseText(List<Expense> recentExpenses) {
        StringBuilder builder = new StringBuilder();
        for (Expense expense : recentExpenses) {
            builder.append(expense.getDate())
                    .append(": £").append(expense.getAmount())
                    .append(" (").append(expense.getCategory()).append(")\n");
        }
        this.expenseSummary = builder.toString();
    }

    public String getExpenseSummary() {
        return expenseSummary;
    }
}