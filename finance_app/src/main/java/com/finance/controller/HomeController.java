package com.finance.controller;

import com.finance.model.Notification;
import com.finance.model.User;
import com.finance.view.HomeView;

public class HomeController {
    private User user;
    private Notification notification;
    private HomeView view;

    public HomeController(User user, Notification notification, HomeView view) {
        this.user = user;
        this.notification = notification;
        this.view = view;
    }

    public void addNotification(String message) {
        notification.addMessage(message);
    }

    public void showHomePage() {
        String fullName = user.getFirstName() + " " + user.getLastName();
        view.displayHome(fullName, notification.getMessages());
    }
}
// test