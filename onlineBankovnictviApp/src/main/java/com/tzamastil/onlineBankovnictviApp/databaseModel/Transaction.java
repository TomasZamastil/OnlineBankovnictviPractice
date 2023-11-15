package com.tzamastil.onlineBankovnictviApp.databaseModel;


import jakarta.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long movementId;

    @ManyToOne
    @JoinColumn(name = "originatingUser")
    private User originatingUser;

    @ManyToOne
    @JoinColumn(name = "receivingUser")
    private User receivingUser;

    private boolean isDeposit;
    private double amount;

    public long getMovementId() {
        return movementId;
    }

    public void setMovementId(long movementId) {
        this.movementId = movementId;
    }

    public User getOriginatingUser() {
        return originatingUser;
    }

    public void setOriginatingUser(User originatingUser) {
        this.originatingUser = originatingUser;
    }

    public User getReceivingUser() {
        return receivingUser;
    }

    public void setReceivingUser(User receivingUser) {
        this.receivingUser = receivingUser;
    }

    public boolean isDeposit() {
        return isDeposit;
    }

    public void setDeposit(boolean deposit) {
        isDeposit = deposit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
