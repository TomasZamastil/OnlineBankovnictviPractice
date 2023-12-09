package com.tzamastil.onlineBankovnictviApp.databaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tzamastil.onlineBankovnictviApp.repos.TransactionRepo;
import com.tzamastil.onlineBankovnictviApp.repos.UserRepo;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class AccountUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    @JsonIgnore
    private long accountNumber;
    @JsonIgnore
    private double balance;
    private String name;
    private String password;
    public static AccountUser currentLoggedAccount = null;


    public AccountUser() {
    }

    public AccountUser(String name, String password, double initialDeposit) {

        this.balance = initialDeposit;
        this.name = name;
        this.password = TotallySecureEncoder.encodePassword(password);
        this.accountNumber = 1000;
    }

    @PostPersist
    private void afterPersist() {
        this.accountNumber = 1000 + this.id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public List<Transaction> getOutgoingTransactions(TransactionRepo transactionRepo) {
        List<Transaction> transactions = new ArrayList<>();
        for (Transaction transaction : transactionRepo.findAll()) {
            if (transaction.getOriginatingUser().getId() == this.id) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    public List<Transaction> getIncomingTransactions(TransactionRepo transactionRepo) {
        List<Transaction> transactions = new ArrayList<>();
        for (Transaction transaction : transactionRepo.findAll()) {
            if (transaction.getReceivingUser().getId() == this.id) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    @Override
    public String toString() {
        return name + " " + accountNumber;
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
}
