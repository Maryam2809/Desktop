package com.finance.view;

import com.finance.model.User;

import javax.swing.*;
import java.util.List;
import java.util.Arrays;

public class ViewFactory {
    public static JPanel getView(String viewType, User user) {
        return switch (viewType) {
            case "Home" -> {
                List<String> notifications = Arrays.asList(
                        "Budget report ready",
                        "Check your goals",
                        "New update available"
                );
                yield new HomeView(user, notifications);
            }
            case "Analytics" -> new AnalyticsPageView();
            case "Input" -> new InputPageView();
            case "Goals" -> new GoalsView();
            default -> throw new IllegalArgumentException("Invalid view type");
        };
    }
}
