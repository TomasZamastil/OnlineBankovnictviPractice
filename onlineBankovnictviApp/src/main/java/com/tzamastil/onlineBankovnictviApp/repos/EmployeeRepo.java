package com.tzamastil.onlineBankovnictviApp.repos;

import com.tzamastil.onlineBankovnictviApp.databaseModel.Employee;
import org.springframework.data.repository.ListCrudRepository;

public interface EmployeeRepo extends ListCrudRepository<Employee, Long> {
}
