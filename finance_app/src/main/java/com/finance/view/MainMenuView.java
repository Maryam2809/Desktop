package com.finance.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.finance.controller.UserController;
import com.finance.dao.FinanceDAO;

public class MainMenuView extends JFrame {
    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private JButton homeButton, analyticsButton, inputButton, goalsButton, logoutButton;

    public MainMenuView() {
        setTitle("Finance Tracker - Main Menu");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar Panel
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(5, 1));
        sidebarPanel.setPreferredSize(new Dimension(200, getHeight()));
        sidebarPanel.setBackground(Color.DARK_GRAY);

        // Buttons for navigation
        homeButton = createSidebarButton("Home");
        analyticsButton = createSidebarButton("Analytics");
        inputButton = createSidebarButton("Input");
        goalsButton = createSidebarButton("Goals");
        logoutButton = createSidebarButton("Logout");

        // Add buttons to sidebar
        sidebarPanel.add(homeButton);
        sidebarPanel.add(analyticsButton);
        sidebarPanel.add(inputButton);
        sidebarPanel.add(goalsButton);
        sidebarPanel.add(logoutButton);

        // Content Panel where the page content is displayed
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        showPanel("Welcome to Finance Tracker!");

        // Add panels to the main frame
        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // Button listeners for navigation
        homeButton.addActionListener(e -> updateSelection(homeButton, new JLabel("Home Page (Coming Soon)")));
        analyticsButton.addActionListener(e -> updateSelection(analyticsButton, new AnalyticsPageView()));
        inputButton.addActionListener(e -> updateSelection(inputButton, new InputPageView()));
        goalsButton.addActionListener(e -> updateSelection(goalsButton, new JLabel("Goals Page (Coming Soon)")));
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginView().setVisible(true);
        });

        // Default selection: Home
        updateSelection(homeButton, new JLabel("Home Page (Coming Soon)"));
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
        // Change background color of selected button
        for (Component comp : sidebarPanel.getComponents()) {
            if (comp instanceof JButton) {
                comp.setBackground(Color.DARK_GRAY);
            }
        }

        selectedButton.setBackground(Color.GRAY);

        // Show the selected panel in the content area
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenuView().setVisible(true));
    }
}