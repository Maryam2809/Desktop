package com.finance.view;

import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    public static void initializeHeadlessMode() {
        System.setProperty("java.awt.headless", "true");
    }
}
