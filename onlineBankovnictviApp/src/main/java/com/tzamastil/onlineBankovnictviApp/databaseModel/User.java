package com.tzamastil.onlineBankovnictviApp.databaseModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;

@Entity
public class User extends Account{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long accountNumber;
    private double balance;

    public User() {
    }

    public User(String name, String password, double balance) {
        super(name, password);
        this.balance = balance;

        // This is a generic value, all accounts will start with a 1 and end with the id assigned at creation
        accountNumber = 1000+id;
    }
}
