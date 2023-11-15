package com.tzamastil.onlineBankovnictviApp.databaseModel;

public class Account {
    private String name;
    private String password;

    public Account() {
    }

    public Account(String name, String password) {
        this.name = name;
        this.password = TotallySecureEncoder.encodePassword(password);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
