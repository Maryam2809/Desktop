package com.finance.view;

import com.finance.dao.FinanceDAO;
import com.finance.model.Expense;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class AnalyticsPageView extends JPanel {
    private FinanceDAO financeDAO;

    public AnalyticsPageView() {
        setLayout(new BorderLayout());
        financeDAO = new FinanceDAO();

        List<Expense> allExpenses = financeDAO.getAllExpenses();

        DefaultPieDataset weeklyDataset = new DefaultPieDataset();
        DefaultPieDataset monthlyDataset = new DefaultPieDataset();

        Map<String, Double> weeklyCategoryTotals = new HashMap<>();
        Map<String, Double> monthlyCategoryTotals = new HashMap<>();

        double totalWeekly = 0;
        double totalMonthly = 0;
        LocalDate now = LocalDate.now();

        for (Expense expense : allExpenses) {
            LocalDate expenseDate = LocalDate.parse(expense.getDate(), DateTimeFormatter.ISO_DATE);

            if (expenseDate.isAfter(now.minusWeeks(1))) {
                weeklyCategoryTotals.put(
                        expense.getCategory(),
                        weeklyCategoryTotals.getOrDefault(expense.getCategory(), 0.0) + expense.getAmount()
                );
                totalWeekly += expense.getAmount();
            }

            if (expenseDate.getMonth() == now.getMonth() && expenseDate.getYear() == now.getYear()) {
                monthlyCategoryTotals.put(
                        expense.getCategory(),
                        monthlyCategoryTotals.getOrDefault(expense.getCategory(), 0.0) + expense.getAmount()
                );
                totalMonthly += expense.getAmount();
            }
        }

        for (Map.Entry<String, Double> entry : weeklyCategoryTotals.entrySet()) {
            weeklyDataset.setValue(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, Double> entry : monthlyCategoryTotals.entrySet()) {
            monthlyDataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart weeklyChart = ChartFactory.createPieChart(
                "Weekly Expense Breakdown",
                weeklyDataset,
                true,
                true,
                false
        );

        JFreeChart monthlyChart = ChartFactory.createPieChart(
                "Monthly Expense Breakdown",
                monthlyDataset,
                true,
                true,
                false
        );

        ChartPanel weeklyPanel = new ChartPanel(weeklyChart);
        ChartPanel monthlyPanel = new ChartPanel(monthlyChart);

        weeklyPanel.setPreferredSize(new Dimension(800, 400));
        monthlyPanel.setPreferredSize(new Dimension(800, 400));

        JPanel chartsPanel = new JPanel();
        chartsPanel.setLayout(new BoxLayout(chartsPanel, BoxLayout.Y_AXIS));
        chartsPanel.add(weeklyPanel);
        chartsPanel.add(monthlyPanel);

        add(chartsPanel, BorderLayout.CENTER);
    }
}