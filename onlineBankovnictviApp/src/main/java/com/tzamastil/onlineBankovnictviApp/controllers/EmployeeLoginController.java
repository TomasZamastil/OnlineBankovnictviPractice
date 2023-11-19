package com.tzamastil.onlineBankovnictviApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeLoginController {
    @RequestMapping("/employeelogin")
    public String getEmployeeLogin() {

        return "employeelogin/employeelogin";
    }
}
