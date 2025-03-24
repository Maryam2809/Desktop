package com.finance.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        homeButton.addActionListener(e -> updateSelection(homeButton, "Home Page (Coming Soon)"));
        analyticsButton.addActionListener(e -> updateSelection(analyticsButton, "Analytics Page (Coming Soon)"));
        inputButton.addActionListener(e -> updateSelection(inputButton, "Input Page (Coming Soon)"));
        goalsButton.addActionListener(e -> updateSelection(goalsButton, "Goals Page (Coming Soon)"));
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginView().setVisible(true);
        });

        updateSelection(homeButton, "Home Page (Coming Soon)");
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

    private void updateSelection(JButton selectedButton, String text) {
        for (Component comp : sidebarPanel.getComponents()) {
            if (comp instanceof JButton) {
                comp.setBackground(Color.DARK_GRAY);
            }
        }

        selectedButton.setBackground(Color.GRAY);

        showPanel(text);
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