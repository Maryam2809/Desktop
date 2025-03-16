package com.finance.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Encrypt the password
    public static String encryptPassword(String plainTextPassword) {
        return passwordEncoder.encode(plainTextPassword);
    }

    // Check if the password matches the hashed password
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return passwordEncoder.matches(plainTextPassword, hashedPassword);
    }
}