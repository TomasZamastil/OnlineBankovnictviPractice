package com.tzamastil.onlineBankovnictviApp.controllers;
import com.tzamastil.onlineBankovnictviApp.databaseModel.Employee;
import com.tzamastil.onlineBankovnictviApp.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final UserRepo userRepo;

    public AdminController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping({"/admin"})
    public String getHome(Model model) {
        if (Employee.currentLoggedAccount != null) {
            String employeeName = Employee.currentLoggedAccount.getName();
            model.addAttribute("employeeName", employeeName);
            return "admin/administration";
        } else {
            return "redirect:";
        }
    }
}
