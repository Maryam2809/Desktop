package com.finance.view;

import com.finance.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class HomeViewTest {

    private HomeView homeView;
    private User mockUser;

    @BeforeAll
    static void checkHeadless() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment detected, skipping tests");
        }
    }

    @BeforeEach
    public void setUp() {
        mockUser = new User("John", "Doe", "john.doe@example.com","password");
        homeView = new HomeView(mockUser, Arrays.asList("New expense added", "Goal reached"));
    }


    @Test
    public void testGreetingLabelText() {
        JLabel greetingLabel = homeView.getGreetingLabel();
        assertNotNull(greetingLabel);
        assertEquals("Hi John!", greetingLabel.getText(), "Greeting label text should be 'Hi John!'");
    }

    @Test
    public void testNotificationsText() {
        JTextArea notificationsArea = homeView.getNotificationsArea();
        assertNotNull(notificationsArea);
        assertTrue(notificationsArea.getText().contains("New expense added"));
        assertTrue(notificationsArea.getText().contains("Goal reached"));
    }

    @Test
    public void testNoNotifications() {
        HomeView homeViewNoNotifications = new HomeView(mockUser, List.of());
        JTextArea notificationsArea = homeViewNoNotifications.getNotificationsArea();
        assertEquals("No notifications.", notificationsArea.getText(), "Notifications area should show 'No notifications.' when empty list is passed");
    }

    @Test
    public void testStatsPanel() {
        JPanel centerPanel = (JPanel) homeView.getComponent(1);
        JPanel statsPanel = (JPanel) centerPanel.getComponent(0);

        assertNotNull(statsPanel, "Stats panel should not be null");

        assertEquals(3, statsPanel.getComponentCount(), "Stats panel should have exactly 3 cards (Expenses, Savings, Goals)");
        Component[] statCards = statsPanel.getComponents();
        assertInstanceOf(JPanel.class, statCards[0], "Expense card should be a JPanel");
        assertInstanceOf(JPanel.class, statCards[1], "Savings card should be a JPanel");
        assertInstanceOf(JPanel.class, statCards[2], "Goals card should be a JPanel");
    }


}
