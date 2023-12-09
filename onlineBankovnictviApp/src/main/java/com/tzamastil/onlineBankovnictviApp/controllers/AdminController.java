package com.tzamastil.onlineBankovnictviApp.controllers;
import com.tzamastil.onlineBankovnictviApp.databaseModel.AccountUser;
import com.tzamastil.onlineBankovnictviApp.databaseModel.Employee;
import com.tzamastil.onlineBankovnictviApp.repos.EmployeeRepo;
import com.tzamastil.onlineBankovnictviApp.repos.TransactionRepo;
import com.tzamastil.onlineBankovnictviApp.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private final UserRepo userRepo;
    private final EmployeeRepo employeeRepo;
    private final TransactionRepo transactionRepo;
    private boolean error = false;

    public AdminController(UserRepo userRepo, EmployeeRepo employeeRepo, TransactionRepo transactionRepo) {
        this.userRepo = userRepo;
        this.employeeRepo = employeeRepo;
        this.transactionRepo = transactionRepo;
    }

    @GetMapping({"/admin"})
    public String getHome(Model model) {
        if (Employee.currentLoggedAccount != null) {
            String employeeName = Employee.currentLoggedAccount.getName();
            model.addAttribute("employeeName", employeeName);
            model.addAttribute("userList", userRepo.findAll());
            model.addAttribute("Transaction", transactionRepo.findAll());
            if (error) {
                model.addAttribute("errorMessage", "Something went wrong, try again");
            } else {
                model.addAttribute("errorMessage", "");
            }
            error = false;
            return "admin/administration";
        } else {
            return "redirect:";
        }
    }

    @PostMapping("/admin")
    @Transactional
    public String logEmployeeIn(@RequestParam(required = false) String adminAccount,
                                @RequestParam(required = false) String name,
                                @RequestParam(required = false) String password,
                                @RequestParam(required = false) Double initialDeposit) {
        if (adminAccount != null) {
            for (Employee employee : employeeRepo.findAll()) {
                if (employee.getName().equals(name)) {
                    error = true;
                    return "redirect:/admin";
                }
            }

            try {
                employeeRepo.save(new Employee(name, password));
            } catch (Exception e) {
                error = true;
            }

        } else {
            for (AccountUser accountUser : userRepo.findAll()) {
                if (accountUser.getName().equals(name)) {
                    error = true;
                    return "redirect:/admin";
                }
            }

            try {
                userRepo.save(new AccountUser(name, password, initialDeposit));
            } catch (Exception e) {
                error = true;
                return "redirect:/admin";
            }

        }
        return "redirect:/admin";
    }
}