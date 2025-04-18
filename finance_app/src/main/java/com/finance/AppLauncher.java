package com.finance;

import javax.swing.*;

import com.finance.view.LoginView;
import com.finance.view.RegisterView;
import com.finance.config.DatabaseConfig;

public class AppLauncher {
    public static void main(String[] args) {
        DatabaseConfig.createTables();

        SwingUtilities.invokeLater(() -> {
            showLoginOrRegisterDialog();
        });
    }

    private static void showLoginOrRegisterDialog() {
        String[] options = {"Login", "Register"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Welcome! Please choose an option to continue:",
                "Authentication Required",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 0) {
            new LoginView().setVisible(true);
        } else if (choice == 1) {
            new RegisterView().setVisible(true);
        } else {
            System.exit(0);
        }
    }
}