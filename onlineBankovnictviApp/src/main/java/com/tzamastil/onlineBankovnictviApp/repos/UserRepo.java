package com.tzamastil.onlineBankovnictviApp.repos;

import com.tzamastil.onlineBankovnictviApp.databaseModel.AccountUser;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepo extends ListCrudRepository<AccountUser, Long> {
}
