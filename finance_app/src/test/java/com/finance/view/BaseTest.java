package com.finance.view;

import org.junit.jupiter.api.BeforeAll;

import java.awt.*;

public class BaseTest {
    @BeforeAll
    public static void initializeHeadlessMode() {
        if (!GraphicsEnvironment.isHeadless()) {
            System.setProperty("java.awt.headless", "true");
        }
    }
}
