package com.finance.controller;

import com.finance.model.Notification;
import com.finance.model.User;
import com.finance.view.HomeView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

class HomeControllerTest {

    private User mockUser;
    private Notification mockNotification;
    private HomeView mockHomeView;
    private HomeController homeController;

    @BeforeEach
    void setUp() {
        mockUser = mock(User.class);
        mockNotification = mock(Notification.class);
        mockHomeView = mock(HomeView.class);

        homeController = new HomeController(mockUser, mockNotification, mockHomeView);
    }

    @Test
    void testAddNotification() {
        String message = "Welcome back!";
        List<String> messages = List.of(message);

        when(mockNotification.getMessages()).thenReturn(messages);

        homeController.addNotification(message);

        verify(mockNotification, times(1)).addMessage(message);
        verify(mockNotification, times(1)).getMessages();
        verify(mockHomeView, times(1)).displayHome(mockUser, messages);
    }

    @Test
    void testShowHomePage() {
        List<String> messages = Arrays.asList("First Notification", "Second Notification");
        when(mockNotification.getMessages()).thenReturn(messages);

        homeController.showHomePage();

        verify(mockNotification, times(1)).getMessages();
        verify(mockHomeView, times(1)).displayHome(mockUser, messages);
    }
}
