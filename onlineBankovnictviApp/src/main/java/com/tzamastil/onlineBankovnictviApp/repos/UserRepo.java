package com.tzamastil.onlineBankovnictviApp.repos;

import com.tzamastil.onlineBankovnictviApp.databaseModel.AccountUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<AccountUser, Long> {
}
