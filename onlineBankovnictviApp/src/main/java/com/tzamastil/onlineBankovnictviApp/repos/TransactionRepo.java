package com.tzamastil.onlineBankovnictviApp.repos;

import com.tzamastil.onlineBankovnictviApp.databaseModel.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {
}
