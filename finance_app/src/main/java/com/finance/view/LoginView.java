package com.finance.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.finance.controller.UserController;
import com.finance.model.User;

public class LoginView extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private UserController userController;

    public LoginView() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        userController = new UserController();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        User user = userController.loginUser(email, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            dispose();
            new MainMenuView(user).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password.");
        }
    }
    public JButton getLoginButton() {return loginButton;}
    public JPasswordField getPasswordField(){
        return passwordField;
    }
    public JTextField getEmailField(){
        return emailField;
    }
}