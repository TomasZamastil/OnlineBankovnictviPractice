package com.tzamastil.onlineBankovnictviApp.controllers;
import com.tzamastil.onlineBankovnictviApp.databaseModel.AccountUser;
import com.tzamastil.onlineBankovnictviApp.databaseModel.TotallySecureEncoder;
import com.tzamastil.onlineBankovnictviApp.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AccountLoginController {

    private final UserRepo userRepo;
    private String wrongNameOrPassword = "";

    public AccountLoginController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping({"/userlogin", "/loginInformationUser"})
    public String getUserLogin(Model model) {
        model.addAttribute("error", wrongNameOrPassword);
        wrongNameOrPassword = "";
        return "userlogin/userLogin";
    }

    @PostMapping("/loginInformationUser")
    public String logUserIn(@RequestParam String name, @RequestParam String password) {
        password = TotallySecureEncoder.encodePassword(password);
        AccountUser.currentLoggedAccount = authenticateAccountUser(name, password);
        if (AccountUser.currentLoggedAccount != null) {
            return "redirect:/useroverview";
        } else {
            wrongNameOrPassword = "Wrong name or password!";
            return "redirect:userlogin";
        }
    }

    private AccountUser authenticateAccountUser(String name, String password) {
        for (AccountUser accountUser : userRepo.findAll()) {
            if (accountUser.getName().equals(name) && accountUser.getPassword().equals(password)) {
                return accountUser;
            }
        }
        return null;
    }
}
