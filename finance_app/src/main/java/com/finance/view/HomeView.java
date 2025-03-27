package com.finance.view;

import java.util.List;

public class HomeView {
    public void displayHome(String userName, List<String> notifications) {
        System.out.println("Hi " + userName + "!");
        System.out.println("Your Notifications:");
        for (String notification : notifications) {
            System.out.println("- " + notification);
        }
    }
}
