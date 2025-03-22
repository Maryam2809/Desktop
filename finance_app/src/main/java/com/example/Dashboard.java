/*package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashbaord extends JFrame {

    // The main container for the cards
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public Dashboard() {
        // Set up the JFrame
        setTitle("Side Menu Example");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the CardLayout and cardPanel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create individual cards (JPanels)
        JPanel card1 = new JPanel();
        card1.add(new JLabel("This is Card 1"));

        JPanel card2 = new JPanel();
        card2.add(new JLabel("This is Card 2"));

        JPanel card3 = new JPanel();
        card3.add(new JLabel("This is Card 3"));

        // Add cards to the cardPanel
        cardPanel.add(card1, "Card 1");
        cardPanel.add(card2, "Card 2");
        cardPanel.add(card3, "Card 3");

        // Create a panel for the side menu
        JPanel sideMenuPanel = new JPanel();
        sideMenuPanel.setLayout(new BoxLayout(sideMenuPanel, BoxLayout.Y_AXIS)); // Stack buttons vertically

        // Create buttons for side menu and add them to the side menu panel
        JButton button1 = new JButton("Go to Card 1");
        JButton button2 = new JButton("Go to Card 2");
        JButton button3 = new JButton("Go to Card 3");

        // Add ActionListeners to the buttons to switch cards
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Card 1"); // Show Card 1
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Card 2"); // Show Card 2
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Card 3"); // Show Card 3
            }
        });

        // Add buttons to the side menu panel
        sideMenuPanel.add(button1);
        sideMenuPanel.add(button2);
        sideMenuPanel.add(button3);

        // Create a main panel to hold both the side menu and the cardPanel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sideMenuPanel, cardPanel);
        splitPane.setDividerLocation(150); // Set the initial width of the side menu

        // Add the split pane to the frame
        add(splitPane);

        // Make the frame visible
        setVisible(true);
    }

    //public static void main(String[] args) {
        // Run the application
      //  SwingUtilities.invokeLater(() -> new SideMenuExample());
    //}
}*/

