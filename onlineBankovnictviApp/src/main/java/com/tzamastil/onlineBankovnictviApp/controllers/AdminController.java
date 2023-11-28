package com.tzamastil.onlineBankovnictviApp.controllers;
import com.tzamastil.onlineBankovnictviApp.databaseModel.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

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
