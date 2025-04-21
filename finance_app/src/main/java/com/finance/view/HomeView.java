package com.finance.view;

import com.finance.dao.FinanceDAO;
import com.finance.model.Goal;
import com.finance.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HomeView extends JPanel {
    private JLabel greetingLabel;
    private JTextArea notificationsArea;

    private JButton addExpenseBtn;
    private JButton viewReportBtn;
    private JButton setGoalBtn;

    private FinanceDAO financeDAO;

    public HomeView(User user, List<String> notifications) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        financeDAO = new FinanceDAO();

        String firstName = user.getFirstName();
        add(createGreetingPanel(firstName), BorderLayout.NORTH);
        add(createCenterPanel(notifications), BorderLayout.CENTER);
    }

    private JPanel createGreetingPanel(String firstName) {
        JPanel greetingPanel = new JPanel(new BorderLayout());
        greetingPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        greetingLabel = new JLabel("Hi " + firstName + "!", SwingConstants.CENTER);
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 24));

        greetingPanel.add(greetingLabel, BorderLayout.CENTER);
        return greetingPanel;
    }

    private JPanel createCenterPanel(List<String> notifications) {
        JPanel centerPanel = new JPanel(new BorderLayout());

        centerPanel.add(createStatsPanel(), BorderLayout.NORTH);

        notificationsArea = new JTextArea();
        notificationsArea.setEditable(false);
        notificationsArea.setFont(new Font("Arial", Font.PLAIN, 16));

        if (notifications == null || notifications.isEmpty()) {
            notificationsArea.setText("No notifications.");
        } else {
            StringBuilder sb = new StringBuilder("Your Notifications:\n");
            for (String notification : notifications) {
                sb.append("- ").append(notification).append("\n");
            }
            notificationsArea.setText(sb.toString());
        }

        JScrollPane scrollPane = new JScrollPane(notificationsArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        return centerPanel;
    }

    private JPanel createStatsPanel() {
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        double totalExpenses = financeDAO.getTotalExpenses();
        String formattedExpenses = String.format("£%,.2f", totalExpenses);

        double totalSavings = financeDAO.getTotalSavings();
        String formattedSavings = String.format("£%,.2f", totalSavings);

        List<Goal> goals = financeDAO.getGoals();
        String goalsSummary = String.format("%d/%d Achieved", goals.size(), 5);

        statsPanel.add(createStatCard("Expenses", formattedExpenses));
        statsPanel.add(createStatCard("Savings", formattedSavings));
        statsPanel.add(createStatCard("Goals", goalsSummary));

        return statsPanel;
    }

    private JPanel createStatCard(String title, String value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        card.setBackground(new Color(240, 240, 240));
        card.setPreferredSize(new Dimension(150, 80));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    public void displayHome(User user, List<String> notifications) {
        greetingLabel.setText("Hi " + user.getFirstName() + "!");

        if (notifications == null || notifications.isEmpty()) {
            notificationsArea.setText("No notifications.");
        } else {
            StringBuilder sb = new StringBuilder("Your Notifications:\n");
            for (String notification : notifications) {
                sb.append("- ").append(notification).append("\n");
            }
            notificationsArea.setText(sb.toString());
        }
    }
}