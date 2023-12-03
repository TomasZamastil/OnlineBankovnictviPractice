package com.tzamastil.onlineBankovnictviApp.bootstrap;

import com.tzamastil.onlineBankovnictviApp.databaseModel.AccountUser;
import com.tzamastil.onlineBankovnictviApp.databaseModel.Employee;
import com.tzamastil.onlineBankovnictviApp.databaseModel.Transaction;
import com.tzamastil.onlineBankovnictviApp.repos.EmployeeRepo;
import com.tzamastil.onlineBankovnictviApp.repos.TransactionRepo;
import com.tzamastil.onlineBankovnictviApp.repos.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final EmployeeRepo employeeRepo;
    private final TransactionRepo transactionRepo;
    private final UserRepo userRepo;

    public BootStrapData(EmployeeRepo employeeRepo, TransactionRepo transactionRepo, UserRepo userRepo) {
        this.employeeRepo = employeeRepo;
        this.transactionRepo = transactionRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Employee tom = new Employee("admin", "asdf1");
        employeeRepo.save(tom);

        AccountUser hana = new AccountUser("Hana Zamastilová", "password1", 10000);
        AccountUser petra = new AccountUser("Petra Zamastilová", "pes", 10000);
        AccountUser zdenek = new AccountUser("Zdeněk Zamastil", "fotbal6", 10000);
        AccountUser vojtech = new AccountUser("Vojtěch Petrásek", "mrkev!", 10000);
        AccountUser krystof = new AccountUser("Kryštof Čížek", "brambory_jsou_nejlepší", 10000);
        userRepo.save(hana);
        userRepo.save(petra);
        userRepo.save(zdenek);
        userRepo.save(vojtech);
        userRepo.save(krystof);

        Transaction rent = new Transaction(hana, petra, 1000);
        Transaction plat = new Transaction(krystof, hana, 5000);
        Transaction nakup = new Transaction(zdenek, krystof, 2000);
        Transaction penizeZaObed = new Transaction(krystof, vojtech, 450);
        if (AccountUser.processTransaction(rent)) {
            transactionRepo.save(rent);
            hana.getTransactions().add(rent);
            petra.getTransactions().add(rent);
        }

        if (AccountUser.processTransaction(plat)) {
            transactionRepo.save(plat);
            krystof.getTransactions().add(plat);
            hana.getTransactions().add(plat);
        }

        if (AccountUser.processTransaction(nakup)) {
            transactionRepo.save(nakup);
            zdenek.getTransactions().add(nakup);
            krystof.getTransactions().add(nakup);
        }

        if (AccountUser.processTransaction(penizeZaObed)) {
            transactionRepo.save(penizeZaObed);
            krystof.getTransactions().add(penizeZaObed);
            vojtech.getTransactions().add(penizeZaObed);
        }
        userRepo.save(hana);
        userRepo.save(petra);
        userRepo.save(zdenek);
        userRepo.save(vojtech);
        userRepo.save(krystof);
    }
}
