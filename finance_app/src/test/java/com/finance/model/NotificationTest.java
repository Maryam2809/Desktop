package com.finance.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationTest {

    private Notification notification;

    @BeforeEach
    public void setUp() {
        notification = new Notification();
    }

    @Test
    public void testAddMessage() {
        notification.addMessage("Budget limit reached");
        List<String> messages = notification.getMessages();

        assertEquals(1, messages.size());
        assertTrue(messages.contains("Budget limit reached"));
    }

    @Test
    public void testAddMultipleMessages() {
        notification.addMessage("First message");
        notification.addMessage("Second message");

        List<String> messages = notification.getMessages();

        assertEquals(2, messages.size());
        assertEquals("First message", messages.get(0));
        assertEquals("Second message", messages.get(1));
    }

    @Test
    public void testGetMessagesReturnsEmptyListInitially() {
        List<String> messages = notification.getMessages();
        assertNotNull(messages);
        assertTrue(messages.isEmpty());
    }
}