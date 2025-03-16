package com.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.swing.SwingUtilities;
import com.finance.view.LoginView;

@SpringBootApplication
public class FinanceApplication {

    public static void main(String[] args) {
        // Start Spring Boot
        SpringApplication.run(FinanceApplication.class, args);

        // Start Swing GUI
        SwingUtilities.invokeLater(() -> new LoginView());
    }
}