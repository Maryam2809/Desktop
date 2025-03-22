package com.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.swing.SwingUtilities;
import com.finance.view.LoginView;

@SpringBootApplication
public class FinanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceApplication.class, args);

        SwingUtilities.invokeLater(() -> new LoginView());
    }
}