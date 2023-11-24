package com.tzamastil.onlineBankovnictviApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountLoginController {
        @GetMapping("/userlogin")
    public String getUserLogin() {

        return "userlogin/userLogin";
    }
}
