package com.company.Domain;

public class User {
    private String username;
    private String passw;

    public User(String username, String passw) {
        this.username = username;
        this.passw = passw;
    }

    public String getUsername() {
        return username;
    }

    public String getPassw() {
        return passw;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", passw='" + passw + '\'' +
                '}';
    }
}