package com.finance.model;

public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String passwordHash;

    public User(int id, String firstname, String lastname, String email, String passwordHash) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}