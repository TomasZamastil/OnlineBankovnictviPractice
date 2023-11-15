package com.tzamastil.onlineBankovnictviApp.databaseModel;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MoneyMovementTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long movementId;
    private long originatingAccountNumber;
    private long receivingAccountNumber;
    private boolean isDeposit;
    private double amount;
}
