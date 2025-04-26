package com.finance.view;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

public class HomeViewTest extends BaseTest {
    private HomeView homeView;

    @BeforeEach
    public void setUp() {
        homeView = mock(HomeView.class); // Mock HomeView class

        // Mocking the components
        JLabel mockGreetingLabel = mock(JLabel.class);
        when(homeView.getGreetingLabel()).thenReturn(mockGreetingLabel);
        when(mockGreetingLabel.getText()).thenReturn("Hi John!");

        JTextArea mockNotificationsArea = mock(JTextArea.class);
        when(homeView.getNotificationsArea()).thenReturn(mockNotificationsArea);
        when(mockNotificationsArea.getText()).thenReturn("New expense added\nGoal reached");
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
}
