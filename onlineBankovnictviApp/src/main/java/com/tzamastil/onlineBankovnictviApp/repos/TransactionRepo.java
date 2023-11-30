package com.tzamastil.onlineBankovnictviApp.repos;

import com.tzamastil.onlineBankovnictviApp.databaseModel.Transaction;
import org.springframework.data.repository.ListCrudRepository;

public interface TransactionRepo extends ListCrudRepository<Transaction, Long> {
}
