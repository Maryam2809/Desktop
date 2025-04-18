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

public class AnalyticsPageView extends JPanel {
    private FinanceDAO financeDAO;

    public AnalyticsPageView() {
        setLayout(new BorderLayout());
        financeDAO = new FinanceDAO();

        List<Expense> allExpenses = financeDAO.getAllExpenses();

        DefaultPieDataset weeklyDataset = new DefaultPieDataset();
        DefaultPieDataset monthlyDataset = new DefaultPieDataset();

        double totalWeekly = 0;
        double totalMonthly = 0;
        LocalDate now = LocalDate.now();

        for (Expense expense : allExpenses) {
            LocalDate expenseDate = LocalDate.parse(expense.getDate(), DateTimeFormatter.ISO_DATE);

            if (expenseDate.isAfter(now.minusWeeks(1))) {
                weeklyDataset.setValue(expense.getCategory(), expense.getAmount());
                totalWeekly += expense.getAmount();
            }

            if (expenseDate.getMonth() == now.getMonth() && expenseDate.getYear() == now.getYear()) {
                monthlyDataset.setValue(expense.getCategory(), expense.getAmount());
                totalMonthly += expense.getAmount();
            }
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