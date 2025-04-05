////package com.finance.view;
////
////import javax.swing.*;
////import java.awt.*;
////import java.util.List;
////
////public class HomeView extends JPanel {
////    private JLabel greetingLabel;
////    private JTextArea notificationsArea;
////
////    public HomeView(String userName, List<String> notifications) {
////        setLayout(new BorderLayout());
////        setPreferredSize(new Dimension(800, 600));
////
////        // Greeting at the top
////        greetingLabel = new JLabel("Hi " + userName + "!", SwingConstants.CENTER);
////        greetingLabel.setFont(new Font("Arial", Font.BOLD, 24));
////        greetingLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
////        add(greetingLabel, BorderLayout.NORTH);
////
////        // Notifications in the center
////        notificationsArea = new JTextArea();
////        notificationsArea.setEditable(false);
////        notificationsArea.setFont(new Font("Arial", Font.PLAIN, 16));
////        if (notifications == null || notifications.isEmpty()) {
////            notificationsArea.setText("No notifications.");
////        } else {
////            StringBuilder sb = new StringBuilder("Your Notifications:\n");
////            for (String notification : notifications) {
////                sb.append("- ").append(notification).append("\n");
////            }
////            notificationsArea.setText(sb.toString());
////        }
////
////        JScrollPane scrollPane = new JScrollPane(notificationsArea);
////        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
////        add(scrollPane, BorderLayout.CENTER);
////    }
////
////    public void displayHome(String userName, List<String> notifications) {
////        greetingLabel.setText("Hi " + userName + "!");
////
////        if (notifications == null || notifications.isEmpty()) {
////            notificationsArea.setText("No notifications.");
////        } else {
////            StringBuilder sb = new StringBuilder("Your Notifications:\n");
////            for (String notification : notifications) {
////                sb.append("- ").append(notification).append("\n");
////            }
////            notificationsArea.setText(sb.toString());
////        }
////    }
////
////}
//
//package com.finance.view;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.List;
//
//public class HomeView extends JPanel {
//    private JLabel greetingLabel;
//    private JTextArea notificationsArea;
//
//    public HomeView(String userName, List<String> notifications) {
//        setLayout(new BorderLayout());
//        setPreferredSize(new Dimension(800, 600));
//
//        // Greeting at the top
//        add(createGreetingPanel(userName), BorderLayout.NORTH);
//
//        // Center area with stats and notifications
//        add(createCenterPanel(notifications), BorderLayout.CENTER);
//
//        // Quick actions at the bottom
//        add(createQuickActionsPanel(), BorderLayout.SOUTH);
//    }
//
//    private JPanel createGreetingPanel(String userName) {
//        JPanel greetingPanel = new JPanel(new BorderLayout());
//        greetingPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
//
//        greetingLabel = new JLabel("Hi " + userName + "!", SwingConstants.CENTER);
//        greetingLabel.setFont(new Font("Arial", Font.BOLD, 24));
//
//        greetingPanel.add(greetingLabel, BorderLayout.CENTER);
//        return greetingPanel;
//    }
//
//    private JPanel createCenterPanel(List<String> notifications) {
//        JPanel centerPanel = new JPanel(new BorderLayout());
//
//        // Stats section
//        centerPanel.add(createStatsPanel(), BorderLayout.NORTH);
//
//        // Notifications area
//        notificationsArea = new JTextArea();
//        notificationsArea.setEditable(false);
//        notificationsArea.setFont(new Font("Arial", Font.PLAIN, 16));
//
//        if (notifications == null || notifications.isEmpty()) {
//            notificationsArea.setText("No notifications.");
//        } else {
//            StringBuilder sb = new StringBuilder("Your Notifications:\n");
//            for (String notification : notifications) {
//                sb.append("- ").append(notification).append("\n");
//            }
//            notificationsArea.setText(sb.toString());
//        }
//
//        JScrollPane scrollPane = new JScrollPane(notificationsArea);
//        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        centerPanel.add(scrollPane, BorderLayout.CENTER);
//
//        return centerPanel;
//    }
//
//    private JPanel createStatsPanel() {
//        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
//        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        statsPanel.add(createStatCard("Expenses", "£1,200"));
//        statsPanel.add(createStatCard("Savings", "£300"));
//        statsPanel.add(createStatCard("Goals", "2/5 Achieved"));
//
//        return statsPanel;
//    }
//
//    private JPanel createStatCard(String title, String value) {
//        JPanel card = new JPanel(new BorderLayout());
//        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
//        card.setBackground(new Color(240, 240, 240));
//        card.setPreferredSize(new Dimension(150, 80));
//
//        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
//
//        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
//        valueLabel.setFont(new Font("Arial", Font.PLAIN, 18));
//
//        card.add(titleLabel, BorderLayout.NORTH);
//        card.add(valueLabel, BorderLayout.CENTER);
//
//        return card;
//    }
//
//    private JPanel createQuickActionsPanel() {
//        JPanel actionsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
//        actionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
//
//        JButton addExpenseBtn = new JButton("Add Expense");
//        JButton viewReportBtn = new JButton("View Report");
//        JButton setGoalBtn = new JButton("Set Goal");
//
//        // You can hook these up to your controller/event listeners as needed
//        addExpenseBtn.addActionListener(e -> System.out.println("Add Expense clicked"));
//        viewReportBtn.addActionListener(e -> System.out.println("View Report clicked"));
//        setGoalBtn.addActionListener(e -> System.out.println("Set Goal clicked"));
//
//        actionsPanel.add(addExpenseBtn);
//        actionsPanel.add(viewReportBtn);
//        actionsPanel.add(setGoalBtn);
//
//        return actionsPanel;
//    }
//
//    public void displayHome(String userName, List<String> notifications) {
//        greetingLabel.setText("Hi " + userName + "!");
//
//        if (notifications == null || notifications.isEmpty()) {
//            notificationsArea.setText("No notifications.");
//        } else {
//            StringBuilder sb = new StringBuilder("Your Notifications:\n");
//            for (String notification : notifications) {
//                sb.append("- ").append(notification).append("\n");
//            }
//            notificationsArea.setText(sb.toString());
//        }
//    }
//}
//

package com.finance.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class HomeView extends JPanel {
    private JLabel greetingLabel;
    private JTextArea notificationsArea;

    private JButton addExpenseBtn;
    private JButton viewReportBtn;
    private JButton setGoalBtn;

    public HomeView(String userName, List<String> notifications) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        // Greeting at the top
        add(createGreetingPanel(userName), BorderLayout.NORTH);

        // Center area with stats and notifications
        add(createCenterPanel(notifications), BorderLayout.CENTER);


    }

    private JPanel createGreetingPanel(String userName) {
        JPanel greetingPanel = new JPanel(new BorderLayout());
        greetingPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        greetingLabel = new JLabel("Hi " + userName + "!", SwingConstants.CENTER);
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 24));

        greetingPanel.add(greetingLabel, BorderLayout.CENTER);
        return greetingPanel;
    }

    private JPanel createCenterPanel(List<String> notifications) {
        JPanel centerPanel = new JPanel(new BorderLayout());

        // Stats section
        centerPanel.add(createStatsPanel(), BorderLayout.NORTH);

        // Notifications area
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

        statsPanel.add(createStatCard("Expenses", "£1,200"));
        statsPanel.add(createStatCard("Savings", "£300"));
        statsPanel.add(createStatCard("Goals", "2/5 Achieved"));

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



    public void displayHome(String userName, List<String> notifications) {
        greetingLabel.setText("Hi " + userName + "!");

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

