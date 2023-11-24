package com.tzamastil.onlineBankovnictviApp.controllers;

import com.tzamastil.onlineBankovnictviApp.databaseModel.Employee;
import com.tzamastil.onlineBankovnictviApp.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeLoginController {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeLoginController(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/employeelogin")
    public String getEmployeeLogin() {
        return "employeelogin/employeelogin";
    }

    @PostMapping("/loginInformation")
    public String logEmployeeIn(@RequestBody String requestBody) {
        System.out.println(employeeRepo.findAll());
        return "employeelogin/employeelogin";
    }
}
