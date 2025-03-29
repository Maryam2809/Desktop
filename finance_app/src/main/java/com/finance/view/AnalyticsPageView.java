package com.finance.view;

import com.finance.model.Expense;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import com.finance.dao.FinanceDAO;

public class AnalyticsPageView extends JPanel {
    private FinanceDAO financeDAO;

    public AnalyticsPageView() {
        setLayout(new BorderLayout());
        financeDAO = new FinanceDAO();

        // Fetch data for the chart
        DefaultPieDataset dataset = new DefaultPieDataset();
        double totalExpenses = 0;
        for (Expense expense : financeDAO.getAllExpenses()) {
            dataset.setValue(expense.getCategory(), expense.getAmount());
            totalExpenses += expense.getAmount();
        }

        // Create Pie chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Expense Breakdown",   // chart title
                dataset,               // dataset
                true,                  // include legend
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        add(chartPanel, BorderLayout.CENTER);
    }
}