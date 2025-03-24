package com.example;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

        public Main(JPanel dashboardPage, JPanel inputPage) {
                setTitle("Finance Tracker");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(1500, 700);
                setLocationRelativeTo(null);

                // main content panel setup
                JPanel menupanel = new JPanel();
                menupanel.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();

                menupanel.setBackground(new Color(245, 245, 245));
                menupanel.setPreferredSize(new Dimension(250, getHeight()));
                menupanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

                //MENUBAR
                Dimension buttonDimension = new Dimension(300, 60);
                JButton homeButton = createStyledButton("Home", buttonDimension);
                JButton dashboardButton = createStyledButton("Dashboard", buttonDimension);
                JButton goalsButton = createStyledButton("Goals", buttonDimension);
                JButton inputButton = createStyledButton("Input", buttonDimension);


                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.weightx = 1.0;
                gbc.ipady = 10;


                gbc.gridx = 0;
                gbc.gridy = 0;
                menupanel.add(homeButton, gbc);

                gbc.gridy = 1;
                menupanel.add(dashboardButton, gbc);

                gbc.gridy = 2;
                menupanel.add(goalsButton, gbc);

                gbc.gridy = 3;
                menupanel.add(inputButton, gbc);

                // Add hover effects to buttons
        /*addButtonHoverEffect(homeButton);
        addButtonHoverEffect(dashboardButton);
        addButtonHoverEffect(goalsButton);
        addButtonHoverEffect(inputButton);*/

                // cardlayout panel to switch between pages
                CardLayout cardLayout = new CardLayout();
                JPanel cardPanel = new JPanel(cardLayout);
                //cardPanel.add(homePage, "HomePage");
                cardPanel.add(dashboardPage, "DashboardPage");
                //cardPanel.add(goalsPage, "GoalsPage");
                cardPanel.add(inputPage, "InputPage");

                homeButton.addActionListener(e -> cardLayout.show(cardPanel, "HomePage"));
                dashboardButton.addActionListener(e -> cardLayout.show(cardPanel, "DashboardPage"));
                //goalsButton.addActionListener(e -> cardLayout.show(cardPanel, "GoalsPage"));
                inputButton.addActionListener(e -> cardLayout.show(cardPanel, "InputPage"));

                add(menupanel, BorderLayout.WEST);
                add(cardPanel, BorderLayout.CENTER);
        }

        private JButton createStyledButton(String text, Dimension dimension) {
                JButton button = new JButton(text);
                button.setPreferredSize(dimension);
                //button.setColor(new Color(55, 66, 82));
                button.setOpaque(true);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
                return button;
        }

        //  hover effect to buttons
    /*private void addButtonHoverEffect(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 82, 97));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(55, 66, 82));
            }
        });
    }*/

        public static void main(String[] args) {
                ExpenseDAO DAO= new ExpenseDAO();
                InputController controller = new InputController(DAO);

                //HomePage homePage = new HomePage();
                DashboardPage dashboardPage = new DashboardPage(controller);
                //GoalsPage goalsPage = new GoalsPage();
                InputPage inputPage = new InputPage(controller);

                SwingUtilities.invokeLater(() -> {
                        Main mainframe = new Main(dashboardPage, inputPage);
                        mainframe.setVisible(true);
                });
        }
}