package com.finance.view;

import com.finance.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainMenuViewTest extends BaseTest {

    private MainMenuView mainMenuView;
    private User mockUser;

    private void clickButtonAndVerifyContent(JButton button, Class<?> expectedView) {
        button.doClick();
        Component content = mainMenuView.getContentPanel().getComponent(0);
        assertTrue(expectedView.isInstance(content),
                "Content panel should display " + expectedView.getSimpleName() + " after clicking the button");
    }

    @BeforeAll
    static void checkHeadless() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment detected, running tests in headless mode.");
        }
    }

    @BeforeEach
    public void setUp() {
        mockUser = new User("John", "Doe", "john.doe@example.com", "password");
        mainMenuView = new MainMenuView(mockUser);
    }

    @Test
    public void testMainMenuInitialization() {
        assertNotNull(mainMenuView, "MainMenuView should not be null");
        assertEquals("Finance Tracker - Main Menu", mainMenuView.getTitle(), "Main menu title is incorrect");
        assertEquals(1300, mainMenuView.getWidth(), "Main menu width should be 1300");
        assertEquals(500, mainMenuView.getHeight(), "Main menu height should be 500");
    }

    @Test
    public void testSidebarButtons() {
        Component[] components = mainMenuView.getSidebarPanel().getComponents();
        assertEquals(5, components.length, "Sidebar should have 5 buttons: Home, Analytics, Input, Goals, Logout");

        for (Component component : components) {
            assertTrue(component instanceof JButton, "Sidebar should contain only buttons");
        }
    }

    @Test
    public void testAnalyticsButtonClick() {
        assertInitialContentPanel();
        clickButtonAndVerifyContent(mainMenuView.getAnalyticsButton(), AnalyticsPageView.class);
    }

    @Test
    public void testInputButtonClick() {
        assertInitialContentPanel();
        clickButtonAndVerifyContent(mainMenuView.getInputButton(), InputPageView.class);
    }

    @Test
    public void testGoalsButtonClick() {
        assertInitialContentPanel();
        clickButtonAndVerifyContent(mainMenuView.getGoalsButton(), GoalsView.class);
    }

    @Test
    public void testHomeButtonClick() {
        JButton homeButton = (JButton) mainMenuView.getSidebarPanel().getComponent(0);
        clickButtonAndVerifyContent(homeButton, HomeView.class);
    }

    @Test
    public void testLogoutButtonClick() {
        JButton logoutButton = (JButton) mainMenuView.getSidebarPanel().getComponent(4);
        logoutButton.doClick();
        assertFalse(mainMenuView.isDisplayable(), "MainMenuView should be disposed after logout button is clicked");
    }

    @Test
    public void testInitialContentPanelText() {
        Component comp = mainMenuView.getContentPanel().getComponent(0);
        assertTrue(comp instanceof JLabel, "First component should be JLabel");
        JLabel label = (JLabel) comp;
        assertEquals("Welcome to Finance Tracker!", label.getText(), "Initial content should display the welcome message");
    }

    @Test
    public void testLayout() {
        assertTrue(mainMenuView.getLayout() instanceof BorderLayout, "Main layout should be BorderLayout");
        assertEquals(200, mainMenuView.getSidebarPanel().getPreferredSize().width, "Sidebar should have width of 200");
        assertTrue(mainMenuView.getSidebarPanel().getLayout() instanceof GridLayout, "Sidebar layout should be GridLayout");
        assertTrue(mainMenuView.getContentPanel().getLayout() instanceof BorderLayout, "Content panel layout should be BorderLayout");
    }

    private void assertInitialContentPanel() {
        assertTrue(mainMenuView.getContentPanel().getComponentCount() > 0, "Content panel should have at least one component initially");
    }
}
