package com.tzamastil.onlineBankovnictviApp.databaseModel;

import jakarta.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;

    @ManyToOne
    @JoinColumn(name = "originating_user")
    private AccountUser originatingUser;

    @ManyToOne
    @JoinColumn(name = "receiving_user")
    private AccountUser receivingUser;

    private double amount;

    public Transaction(AccountUser originatingUser, AccountUser receivingUser, double amount) {
        this.originatingUser = originatingUser;
        this.receivingUser = receivingUser;
        this.amount = amount;
    }

    public Transaction() {
    }

    public AccountUser getOriginatingUser() {
        return originatingUser;
    }

    public AccountUser getReceivingUser() {
        return receivingUser;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        return transactionId == that.transactionId;
    }

    @Override
    public int hashCode() {
        return (int) (transactionId ^ (transactionId >>> 32));
    }
}
