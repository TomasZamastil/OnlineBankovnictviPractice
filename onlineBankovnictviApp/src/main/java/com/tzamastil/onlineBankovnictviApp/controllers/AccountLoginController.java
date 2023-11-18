package com.tzamastil.onlineBankovnictviApp.controllers;

import com.tzamastil.onlineBankovnictviApp.repos.UserRepo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class AccountLoginController {
    private final UserRepo userRepo;

    public AccountLoginController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping("/userlogin")
    public String getAccountUsers(Model model) {

        model.addAttribute("userlogin", userRepo.findAll());

        return "userlogin/userLogin";
    }
}
