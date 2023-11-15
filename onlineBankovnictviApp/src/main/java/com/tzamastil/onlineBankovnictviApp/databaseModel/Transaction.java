package com.tzamastil.onlineBankovnictviApp.databaseModel;


import jakarta.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;

    @ManyToOne
    @JoinColumn(name = "originatingUser")
    private AccountUser originatingUser;

    @ManyToOne
    @JoinColumn(name = "receivingUser")
    private AccountUser receivingUser;

    private double amount;

    public Transaction(AccountUser originatingUser, AccountUser receivingUser, double amount) {
        this.originatingUser = originatingUser;
        this.receivingUser = receivingUser;
        this.amount = amount;
    }

    public Transaction() {
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long movementId) {
        this.transactionId = movementId;
    }

    public AccountUser getOriginatingUser() {
        return originatingUser;
    }

    public void setOriginatingUser(AccountUser originatingUser) {
        this.originatingUser = originatingUser;
    }

    public AccountUser getReceivingUser() {
        return receivingUser;
    }

    public void setReceivingUser(AccountUser receivingUser) {
        this.receivingUser = receivingUser;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
