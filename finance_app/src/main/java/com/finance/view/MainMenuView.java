package com.finance.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;

import com.finance.config.DatabaseConfig;
import com.finance.model.User;

public class MainMenuView extends JFrame {
    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private JButton homeButton, analyticsButton, inputButton, goalsButton, logoutButton;

    private final User user;

    public MainMenuView(User user) {
        this.user = user;

        setTitle("Finance Tracker");
        setSize(1300, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setAlwaysOnTop(true);

        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(5, 1));
        sidebarPanel.setPreferredSize(new Dimension(200, getHeight()));
        sidebarPanel.setBackground(Color.DARK_GRAY);

        homeButton = createSidebarButton("Home");
        analyticsButton = createSidebarButton("Analytics");
        inputButton = createSidebarButton("Input");
        goalsButton = createSidebarButton("Goals");
        logoutButton = createSidebarButton("Logout");

        sidebarPanel.add(homeButton);
        sidebarPanel.add(analyticsButton);
        sidebarPanel.add(inputButton);
        sidebarPanel.add(goalsButton);
        sidebarPanel.add(logoutButton);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        showPanel("Welcome to Finance Tracker!");

        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        homeButton.addActionListener(e -> {
            List<String> notifications = Arrays.asList(
                    "Budget report ready",
                    "Check your goals",
                    "New update available"
            );
            updateSelection(homeButton, new HomeView(user, notifications));
        });

        analyticsButton.addActionListener(e -> updateSelection(analyticsButton, new AnalyticsPageView()));
        inputButton.addActionListener(e -> updateSelection(inputButton, new InputPageView()));
        goalsButton.addActionListener(e -> updateSelection(goalsButton, new GoalsView()));
        logoutButton.addActionListener(e -> {
            dispose();
            new com.finance.AppLauncher().main(null);
        });

        setVisible(true);
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.DARK_GRAY);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }

    private void updateSelection(JButton selectedButton, Component component) {
        for (Component comp : sidebarPanel.getComponents()) {
            if (comp instanceof JButton) {
                comp.setBackground(Color.DARK_GRAY);
            }
        }

        selectedButton.setBackground(Color.GRAY);
        contentPanel.removeAll();
        contentPanel.add(component, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showPanel(String text) {
        contentPanel.removeAll();
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        contentPanel.add(label, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    public JPanel getSidebarPanel() {
        return sidebarPanel;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public JButton getAnalyticsButton() {
        return analyticsButton;
    }
    public JButton getHomeButton() {
        return homeButton;
    }
    public JButton getInputButton() {
        return inputButton;
    }
    public JButton getGoalsButton() {
        return goalsButton;
    }

}