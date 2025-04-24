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
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class AnalyticsPageView extends JPanel {
    private FinanceDAO financeDAO;
    public String expenseSummary;

    public AnalyticsPageView() {
        setLayout(new BorderLayout());
        financeDAO = new FinanceDAO();

        List<Expense> allExpenses = financeDAO.getAllExpenses();
        List<Expense> recentExpenses = financeDAO.getRecentExpenses();

        DefaultPieDataset weeklyDataset = new DefaultPieDataset();
        DefaultPieDataset monthlyDataset = new DefaultPieDataset();
        DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();

        Map<String, Double> weeklyCategoryTotals = new HashMap<>();
        Map<String, Double> monthlyCategoryTotals = new HashMap<>();
        Map<String, Double> weeklyCategoryTotalsDated = new TreeMap<>();

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


            weeklyCategoryTotalsDated.put(expense.getDate(),expense.getAmount());
        }

        for (Map.Entry<String, Double> entry : weeklyCategoryTotals.entrySet()) {
            weeklyDataset.setValue(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, Double> entry : monthlyCategoryTotals.entrySet()) {
            monthlyDataset.setValue(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, Double> entry : weeklyCategoryTotalsDated.entrySet()) {
            lineDataset.addValue(entry.getValue(), "Expenses", entry.getKey());
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

        JFreeChart lineGraph = ChartFactory.createLineChart(
                "Weeks Expenses",
                "Date",
                "Amount (£)",
                lineDataset
        );

        ChartPanel weeklyPanel = new ChartPanel(weeklyChart);
        ChartPanel monthlyPanel = new ChartPanel(monthlyChart);
        ChartPanel linePanel = new ChartPanel(lineGraph);

        getRecentExpenseText(recentExpenses);

        JTextArea recentTextArea = new JTextArea(expenseSummary);
        recentTextArea.setEditable(false);
        recentTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        recentTextArea.setLineWrap(true);
        recentTextArea.setWrapStyleWord(true);

        JPanel recentPanel = new JPanel(new BorderLayout());
        recentPanel.add(new JScrollPane(recentTextArea), BorderLayout.CENTER);
        recentPanel.add(recentTextArea);

        weeklyPanel.setPreferredSize(new Dimension(2000, 400));
        monthlyPanel.setPreferredSize(new Dimension(2000, 400));
        linePanel.setPreferredSize(new Dimension(2000, 400));
        recentPanel.setPreferredSize(new Dimension(2000, 400));

        JPanel chartsPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        chartsPanel.add(weeklyPanel);
        chartsPanel.add(monthlyPanel);
        chartsPanel.add(linePanel);
        chartsPanel.add(recentPanel);

        add(chartsPanel, BorderLayout.CENTER);
    }

    public void getRecentExpenseText (List<Expense> recentExpenses){
        StringBuilder builder = new StringBuilder();
        builder.append("Recent Transactions").append("\n");
        for (Expense expense : recentExpenses){
            String date = expense.getDate();
            String description = expense.getDescription();
            String amount = Double.toString(expense.getAmount());
            builder.append(date).append(' ').append(description).append(' ').append("£").append(amount).append("\n");
        }
        expenseSummary = builder.toString();
    }
}

//weekly income vs weekly expenses to add