package com.tzamastil.onlineBankovnictviApp.controllers;

import com.tzamastil.onlineBankovnictviApp.databaseModel.AccountUser;
import com.tzamastil.onlineBankovnictviApp.databaseModel.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/home", "", "/"})
    public String getHome() {
        Employee.currentLoggedAccount = null;
        AccountUser.currentLoggedAccount = null;
        return "home/homePage";
    }
}
