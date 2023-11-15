package com.tzamastil.onlineBankovnictviApp.databaseModel;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends Account{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long accountNumber;
    private double balance;

    @OneToMany(mappedBy = "originatingUser", cascade = CascadeType.ALL)
    private List<Transaction> originatingTransactions;
    @OneToMany(mappedBy = "receivingUser", cascade = CascadeType.ALL)
    private List<Transaction> receivedTransactions;

    public User() {
    }

    public User(String name, String password, double balance) {
        super(name, password);
        this.balance = balance;

        // This is a generic value, all accounts will start with a 1 and end with the id assigned at creation
        this.accountNumber = 1000+id;
        this.originatingTransactions = new ArrayList<>();
        this.receivedTransactions = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getOriginatingTransactions() {
        return originatingTransactions;
    }

    public void setOriginatingTransactions(List<Transaction> originatingTransactions) {
        this.originatingTransactions = originatingTransactions;
    }

    public List<Transaction> getReceivedTransactions() {
        return receivedTransactions;
    }

    public void setReceivedTransactions(List<Transaction> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }
}
