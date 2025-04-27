package com.finance.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.finance.controller.UserController;
import com.finance.model.User;

public class RegisterView extends JFrame {
    private JTextField firstNameField, lastNameField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterView() {
        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        registerButton = new JButton("Register");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(registerButton);

        add(panel);

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

        User user = new User(firstName, lastName, email, password);
        UserController userController = new UserController();
        boolean success = userController.registerUser(user);

        if (success) {
            JOptionPane.showMessageDialog(this, "Registration Successful!");
            dispose();
            new MainMenuView(user).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Registration Failed! Please try again.");
        }
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(JButton registerButton) {
        this.registerButton = registerButton;
    }
}