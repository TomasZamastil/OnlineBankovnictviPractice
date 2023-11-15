package com.tzamastil.onlineBankovnictviApp.databaseModel;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountUser{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long accountNumber;
    private double balance;
    private String name;
    private String password;


    @OneToMany(mappedBy = "receivingUser", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public AccountUser() {
    }

    public AccountUser(String name, String password, double initialDeposit) {

        this.transactions = new ArrayList<>();
        this.balance = initialDeposit;
        this.name = name;
        this.password = TotallySecureEncoder.encodePassword(password);
    }

    @PostPersist
    private void afterPersist() {
        this.accountNumber = 1000 + this.id;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountUser accountUser = (AccountUser) o;

        return id == accountUser.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public static boolean processTransaction(Transaction transaction) {
        if (transaction.getAmount() <= transaction.getOriginatingUser().getBalance()) {
            transaction.getOriginatingUser().setBalance(transaction.getOriginatingUser().getBalance() - transaction.getAmount());
            transaction.getReceivingUser().setBalance(transaction.getReceivingUser().getBalance() + transaction.getAmount());
            return true;
        } else {
            return false;
        }
    }
}
