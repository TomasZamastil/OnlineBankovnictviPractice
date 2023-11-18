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

    @RequestMapping("/users")
    public String getAccountUsers(Model model) {

        model.addAttribute("users", userRepo.findAll());

        return "users";
    }
}
