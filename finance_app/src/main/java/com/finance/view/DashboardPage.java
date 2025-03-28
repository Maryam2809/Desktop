package org.example;

import org.knowm.xchart.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DashboardPage extends JPanel {

    private Map<String, Integer> totals;
    private Map<Integer, Integer> monthsExpenses;
    private List<Integer> dates;
    private List<Integer> totalExpenses;

    public DashboardPage(InputController controller) {
        this.totals = controller.getExpensesByCategory();
        this.monthsExpenses = controller.getMonthsExpenses();

        this.dates = new ArrayList<>(monthsExpenses.keySet());
        this.totalExpenses = new ArrayList<>(monthsExpenses.values());

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        addPieChart(gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        addLineChart(gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        addRecentExpenses(gbc);
    }

    private void addPieChart(GridBagConstraints gbc) {
        if (totals != null && !totals.isEmpty()) {
            PieChart pieChart = new PieChartBuilder().width(300).height(200).title("Expense Categories").build();
            for (Map.Entry<String, Integer> entry : totals.entrySet()) {
                pieChart.addSeries(entry.getKey(), entry.getValue());
            }
            XChartPanel chartPanel = new   XChartPanel(pieChart);
            chartPanel.setPreferredSize(new Dimension(400, 300));
            add(chartPanel, gbc);
        } else {
            JLabel noDataLabel = new JLabel("No data to display yet");
            add(noDataLabel, gbc);
        }
    }

    private void addLineChart(GridBagConstraints gbc) {
        if (dates != null && !dates.isEmpty()) {
            XYChart lineChart = new XYChartBuilder()
                    .width(300).height(200)
                    .title("Expenses Over Last 30 Days")
                    .xAxisTitle("Date")
                    .yAxisTitle("Amount")
                    .build();

            lineChart.addSeries("Expenses", dates, totalExpenses);
            XChartPanel chartPanel = new   XChartPanel(lineChart);
            chartPanel.setPreferredSize(new Dimension(400, 300));
            add(chartPanel, gbc);
        } else {
            JLabel noDataLabel = new JLabel("No recent expense data available");
            add(noDataLabel, gbc);
        }
    }

    //mock data for now
    private void addRecentExpenses(GridBagConstraints gbc) {
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("Rent - $1200");
        model.addElement("Groceries - $250");
        model.addElement("Salary - $5000");
        model.addElement("Investment - $500");

        JList<String> recentExpensesList = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(recentExpensesList);
        scrollPane.setPreferredSize(new Dimension(300, 500));
        add(scrollPane, gbc);
    }
}
