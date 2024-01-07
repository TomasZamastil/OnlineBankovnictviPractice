package com.tzamastil.onlineBankovnictviApp.databaseModel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountUserTest {

    AccountUser accountUser = new AccountUser("Tomas", "heslo1", 1000);


    @Test
    void getId() {
        assertEquals(0, accountUser.getId());
    }

    @Test
    void getName() {
        assertEquals("Tomas", accountUser.getName());
    }

    @Test
    void getPassword() {
        assertEquals(TotallySecureEncoder.encodePassword("heslo1"), accountUser.getPassword());
    }

    @Test
    void getBalance() {
        assertEquals(1000, accountUser.getBalance());
    }

    @Test
    void setBalance() {
        accountUser.setBalance(1500);
        assertEquals(1500, accountUser.getBalance());
    }

    @Test
    void getAccountNumber() {
        assertEquals(1000, accountUser.getAccountNumber());
    }
}