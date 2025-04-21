package com.finance.model;

import java.util.ArrayList;
import java.util.List;

public class Notification {
    private List<String> messages;

    public Notification() {
        this.messages = new ArrayList<>();
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}