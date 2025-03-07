package com.finance.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application {
    private UserController userController;

    public Login() {
        this.userController = new UserController();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        Label messageLabel = new Label();

        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            boolean success = userController.login(email, password);
            messageLabel.setText(success ? "Login Successful" : "Invalid Credentials.");
        });

        registerButton.setOnAction(e -> {
            // Open Registration View
        });

        VBox layout = new VBox(10, emailLabel, emailField, passwordLabel, passwordField, loginButton, registerButton, messageLabel);
        layout.setAlignment(Pos.CENTER);
        primaryStage.setScene(new Scene(layout, 300, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}