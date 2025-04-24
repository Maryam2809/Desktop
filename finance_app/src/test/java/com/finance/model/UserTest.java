package com.finance.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("Jane", "Doe", "jane.doe@example.com", "securepassword123");
    }

    @Test
    public void testGetters() {
        assertEquals("Jane", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("securepassword123", user.getPassword());
    }

    @Test
    public void testSetFirstName() {
        user.setFirstName("Emily");
        assertEquals("Emily", user.getFirstName());
    }

    @Test
    public void testSetLastName() {
        user.setLastName("Smith");
        assertEquals("Smith", user.getLastName());
    }

    @Test
    public void testSetEmail() {
        user.setEmail("emily.smith@example.com");
        assertEquals("emily.smith@example.com", user.getEmail());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("newpassword456");
        assertEquals("newpassword456", user.getPassword());
    }
}