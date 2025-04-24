package com.finance.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordUtilTest {
    String plainText = "MyPassword123!";
    String encrypted = PasswordUtil.encryptPassword(plainText);

    @Test
    void testEncryptPassword_NotNullAndNotEqualToPlainText() {
        assertNotNull(encrypted, "Encrypted password should not be null");
        assertNotEquals(plainText, encrypted, "Encrypted password should not equal plain text");
    }

    @Test
    void testCheckPassword_MatchingPassword() {
        assertTrue(PasswordUtil.checkPassword(plainText, encrypted), "Password should match the encrypted value");
    }

    @Test
    void testCheckPassword_NonMatchingPassword() {
        assertFalse(PasswordUtil.checkPassword("WrongPassword", encrypted), "Password should not match the encrypted value");
    }
}
