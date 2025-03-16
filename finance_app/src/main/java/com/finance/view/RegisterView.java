package com.finance.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.finance.controller.UserController; // The controller to handle user actions

public class RegisterView extends JFrame {
    private JTextField firstNameField, lastNameField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterView() {
        // Setup the frame
        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI components
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        registerButton = new JButton("Register");

        // Set up layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2)); // 5 rows, 2 columns
        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(registerButton);

        add(panel);

        // Add event listener to the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });
    }

    private void handleRegister() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        // Use a controller to handle registration logic
        UserController userController = new UserController();
        boolean success = userController.registerUser(firstName, lastName, email, password);

        if (success) {
            JOptionPane.showMessageDialog(this, "Registration Successful!");
            dispose();  // Close registration window
        } else {
            JOptionPane.showMessageDialog(this, "Registration Failed! Please try again.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegisterView().setVisible(true);
            }
        });
    }
}