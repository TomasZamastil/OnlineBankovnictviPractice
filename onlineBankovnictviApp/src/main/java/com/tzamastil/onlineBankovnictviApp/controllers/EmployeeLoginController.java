package com.tzamastil.onlineBankovnictviApp.controllers;

import com.tzamastil.onlineBankovnictviApp.databaseModel.Employee;
import com.tzamastil.onlineBankovnictviApp.databaseModel.TotallySecureEncoder;
import com.tzamastil.onlineBankovnictviApp.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeLoginController {

    private final EmployeeRepo employeeRepo;
    private boolean wrongNameOrPassword = false;

    @Autowired
    public EmployeeLoginController(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @GetMapping({"/employeelogin", "/loginInformation"})
    public String getEmployeeLogin(Model model) {
        if (wrongNameOrPassword) {
            model.addAttribute("error", "Wrong name or password!");
        } else {
            model.addAttribute("error", "");
        }
        wrongNameOrPassword = false;
        return "employeelogin/employeelogin";
    }

    @PostMapping("/loginInformation")
    public String logEmployeeIn(@RequestParam String name, @RequestParam String password) {
        password = TotallySecureEncoder.encodePassword(password);
        Employee.currentLoggedAccount = authenticateEmployee(name, password);
        if (Employee.currentLoggedAccount != null) {
            return "redirect:/admin";
        } else {
            wrongNameOrPassword = true;
            return "redirect:employeelogin";
        }
    }

    private Employee authenticateEmployee(String name, String password) {
        for (Employee employee : employeeRepo.findAll()) {
            if (employee.getName().equals(name) && employee.getPassword().equals(password)) {
                return employee;
            }
        }
        return null;
    }
}
