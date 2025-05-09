package com.finance.view;

import javax.swing.*;
import java.awt.*;

import com.finance.AppLauncher;
import com.finance.model.User;

public class MainMenuView extends JFrame {
    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private JButton homeButton;
    private JButton analyticsButton;
    private JButton inputButton;
    private JButton goalsButton;
    private final User user;

    public MainMenuView(User user) {
        this.user = user;

        setTitle("Finance Tracker");
        setSize(1300, 500);
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
        JButton logoutButton = createSidebarButton("Logout");

        sidebarPanel.add(homeButton);
        sidebarPanel.add(analyticsButton);
        sidebarPanel.add(inputButton);
        sidebarPanel.add(goalsButton);
        sidebarPanel.add(logoutButton);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        showPanel();

        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        homeButton.addActionListener(e -> {
            updateSelection(homeButton, ViewFactory.getView("Home", user));
        });

        analyticsButton.addActionListener(e -> updateSelection(analyticsButton, ViewFactory.getView("Analytics", user)));
        inputButton.addActionListener(e -> updateSelection(inputButton, ViewFactory.getView("Input", user)));
        goalsButton.addActionListener(e -> updateSelection(goalsButton, ViewFactory.getView("Goals", user)));
        logoutButton.addActionListener(e -> {
            dispose();
            AppLauncher.main(null);
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

    private void showPanel() {
        contentPanel.removeAll();
        JLabel label = new JLabel("Welcome to Finance Tracker!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        contentPanel.add(label, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}

