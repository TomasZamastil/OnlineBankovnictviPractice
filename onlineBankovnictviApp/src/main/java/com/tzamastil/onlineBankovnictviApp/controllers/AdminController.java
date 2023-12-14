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
    private String error = "";

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
            model.addAttribute("transaction", transactionRepo.findAll());
            model.addAttribute("errorMessage", error);
            error = "";
            return "admin/administration";
        } else {
            return "redirect:";
        }
    }

    @PostMapping("/accountDeletion")
    @Transactional
    public String deleteAccount(@RequestParam(name = "userId") long id) {
        userRepo.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/accountCreation")
    @Transactional
    public String createAccount(@RequestParam(required = false) String adminAccount,
                                @RequestParam(required = false) String name,
                                @RequestParam(required = false) String password,
                                @RequestParam(required = false) Double initialDeposit) {
        if (name.isEmpty() || password.isEmpty()) {
            error = "Values Name and Password cannot be empty";
            return "redirect:/admin";
        }
        if (adminAccount != null) {
            for (Employee employee : employeeRepo.findAll()) {
                if (employee.getName().equals(name)) {
                    error = "Account with this user name already exists!";
                    return "redirect:/admin";
                }
            }
                employeeRepo.save(new Employee(name, password));

        } else {
            for (AccountUser accountUser : userRepo.findAll()) {
                if (accountUser.getName().equals(name)) {
                    error = "Account with this user name already exists!";
                    return "redirect:/admin";
                }
            }

            try {
                userRepo.save(new AccountUser(name, password, initialDeposit));
            } catch (Exception e) {
                error = "Customer accounts require initial deposit";
                return "redirect:/admin";
            }
        }
        return "redirect:/admin";
    }
}