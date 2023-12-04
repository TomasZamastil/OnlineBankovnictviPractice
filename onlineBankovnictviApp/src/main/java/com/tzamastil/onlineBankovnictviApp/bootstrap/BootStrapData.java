package com.tzamastil.onlineBankovnictviApp.bootstrap;

import com.tzamastil.onlineBankovnictviApp.databaseModel.AccountUser;
import com.tzamastil.onlineBankovnictviApp.databaseModel.Employee;
import com.tzamastil.onlineBankovnictviApp.repos.EmployeeRepo;
import com.tzamastil.onlineBankovnictviApp.repos.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final EmployeeRepo employeeRepo;
    private final UserRepo userRepo;

    public BootStrapData(EmployeeRepo employeeRepo, UserRepo userRepo) {
        this.employeeRepo = employeeRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        employeeRepo.save(new Employee("admin", "heslo123"));

        userRepo.save(new AccountUser("Pavel Novak", "pavnov1", 10000));
        userRepo.save(new AccountUser("Jana Novakova", "jannov2", 10000));


    }
}
