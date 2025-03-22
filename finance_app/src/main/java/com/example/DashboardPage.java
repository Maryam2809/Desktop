package com.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class DashboardPage extends JPanel {

    public DashboardPage() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Create charts
        ChartPanel pieChart = new ChartPanel(createPieChart());
        ChartPanel lineChart = new ChartPanel(createLineChart());
        ChartPanel barChart1 = new ChartPanel(createBarChart("Income vs Expenses"));
        ChartPanel barChart2 = new ChartPanel(createBarChart("Savings vs Investments"));

        // Create a scrollable list
        JScrollPane listPanel = new JScrollPane(createTransactionList());

        // Arrange charts in a grid layout
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1; gbc.gridheight = 1;
        add(pieChart, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        add(lineChart, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(barChart1, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        add(barChart2, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.weighty = 0.5;
        add(listPanel, gbc);
    }

    // PIE CHART
    private JFreeChart createPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Rent", 30);
        dataset.setValue("Groceries", 20);
        dataset.setValue("Utilities", 15);
        dataset.setValue("Savings", 25);
        dataset.setValue("Entertainment", 10);

        return ChartFactory.createPieChart("Expense Breakdown", dataset, true, true, false);
    }

    // LINE CHART
    private JFreeChart createLineChart() {
        XYSeries series = new XYSeries("Monthly Balance");
        series.add(1, 500);
        series.add(2, 700);
        series.add(3, 800);
        series.add(4, 750);
        series.add(5, 900);
        XYSeriesCollection dataset = new XYSeriesCollection(series);

        return ChartFactory.createXYLineChart("Financial Growth", "Month", "Balance", dataset, PlotOrientation.VERTICAL, true, true, false);
    }

    // BAR CHART
    private JFreeChart createBarChart(String title) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(5000, "Income", "Jan");
        dataset.addValue(5200, "Income", "Feb");
        dataset.addValue(4800, "Income", "Mar");

        dataset.addValue(3000, "Expenses", "Jan");
        dataset.addValue(3100, "Expenses", "Feb");
        dataset.addValue(2800, "Expenses", "Mar");

        return ChartFactory.createBarChart(title, "Category", "Amount ($)", dataset, PlotOrientation.VERTICAL, true, true, false);
    }

    // LIST COMPONENT (Transaction History)
    private JList<String> createTransactionList() {
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("Rent - $1200");
        model.addElement("Salary - $5000");
        model.addElement("Groceries - $250");
        model.addElement("Internet Bill - $60");
        model.addElement("Investment - $500");

        JList<String> list = new JList<>(model);
        list.setFont(new Font("Arial", Font.PLAIN, 14));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return list;
    }
}




// dashbord page with graphs (layout manager)
//menubar - buttons - click --> different component
//different components themselves


//card lyapout manager
//menubar - on click different card

//Jpanel panel= Jpanel();
//Cardamnaber cardmamager= cardmanager();
//cardmanager.add(panel);
//panel.add(button1)
//button1.actionlistener(new ActionListener() -> cardmanager.changecardtocard 1);
//card1= class name



//menubar and main page
//dashboard page - graphs and stuff
//home page




//card panel - add different cards
//menubar panel - add buttons- button click to different cards in card panel
// make classes public ?