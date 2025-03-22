package com.example;

import javax.swing.*;
import java.awt.*;

public class FrameMain extends JFrame {

    public FrameMain(JPanel dashboardPage, JPanel goalsPage, JPanel inputPage, JPanel homePage) {
        setTitle("Finance Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 700);
        setLocationRelativeTo(null);

        // Main content panel setup
        JPanel menupanel = new JPanel();
        menupanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        menupanel.setBackground(new Color(245, 245, 245));
        menupanel.setPreferredSize(new Dimension(250, getHeight()));
        menupanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create buttons with refined design
        Dimension buttonDimension = new Dimension(300, 60);
        JButton homeButton = createStyledButton("Home", buttonDimension);
        JButton dashboardButton = createStyledButton("Dashboard", buttonDimension);
        JButton goalsButton = createStyledButton("Goals", buttonDimension);
        JButton inputButton = createStyledButton("Input", buttonDimension);

        // Set GridBagConstraints for equal width buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Ensure the buttons expand horizontally
        gbc.weightx = 1.0;  // Allow the buttons to take up equal space horizontally
        gbc.ipady = 10;     // Optional: Adds padding inside the button vertically

        // Add buttons to the menu panel using GridBagConstraints
        gbc.gridx = 0; // Column position for all buttons
        gbc.gridy = 0; // Row position for home button
        menupanel.add(homeButton, gbc);

        gbc.gridy = 1; // Row position for dashboard button
        menupanel.add(dashboardButton, gbc);

        gbc.gridy = 2; // Row position for goals button
        menupanel.add(goalsButton, gbc);

        gbc.gridy = 3; // Row position for input button
        menupanel.add(inputButton, gbc);

        // Add hover effects to buttons
        /*addButtonHoverEffect(homeButton);
        addButtonHoverEffect(dashboardButton);
        addButtonHoverEffect(goalsButton);
        addButtonHoverEffect(inputButton);*/

        // Create a CardLayout panel to switch between different pages
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        cardPanel.add(homePage, "HomePage");
        cardPanel.add(dashboardPage, "DashboardPage");
        cardPanel.add(goalsPage, "GoalsPage");
        cardPanel.add(inputPage, "InputPage");

        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "HomePage"));
        dashboardButton.addActionListener(e -> cardLayout.show(cardPanel, "DashboardPage"));
        goalsButton.addActionListener(e -> cardLayout.show(cardPanel, "GoalsPage"));
        inputButton.addActionListener(e -> cardLayout.show(cardPanel, "InputPage"));

        add(menupanel, BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);
    }

    // Method to create and style buttons with soft colors
    private JButton createStyledButton(String text, Dimension dimension) {
        JButton button = new JButton(text);
        button.setPreferredSize(dimension);  // Set preferred size for buttons
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        return button;
    }

    // Method to add hover effect to buttons
    /*private void addButtonHoverEffect(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 82, 97)); // Slightly darker shade on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(55, 66, 82)); // Revert to the original color on mouse exit
            }
        });
    }*/

    public static void main(String[] args) {
        HomePage homePage = new HomePage();
        DashboardPage dashboardPage = new DashboardPage();
        GoalsPage goalsPage = new GoalsPage();
        InputPage inputPage = new InputPage();

        SwingUtilities.invokeLater(() -> {
            FrameMain frame = new FrameMain(dashboardPage, goalsPage, inputPage, homePage);
            frame.setVisible(true);
        });
    }
}


//make the dashboard - mock data - but start thinking about classes and database
//input page and class and connect to dashboard
//make the whole application roughly and then see

//frame --> panel --> buttons , set layout of parent