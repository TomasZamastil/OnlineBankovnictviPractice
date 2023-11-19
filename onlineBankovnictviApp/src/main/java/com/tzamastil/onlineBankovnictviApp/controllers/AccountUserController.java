package com.tzamastil.onlineBankovnictviApp.controllers;

import com.tzamastil.onlineBankovnictviApp.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountUserController {

    private final UserRepo userRepo;

    public AccountUserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping("/useroverview")
    public String getAccountUsers(Model model) {

        return "useroverview/accountView";
    }
}
