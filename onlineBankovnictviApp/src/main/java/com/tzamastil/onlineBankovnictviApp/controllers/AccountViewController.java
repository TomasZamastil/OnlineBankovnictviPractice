package com.tzamastil.onlineBankovnictviApp.controllers;
import com.tzamastil.onlineBankovnictviApp.databaseModel.AccountUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountViewController {

    @RequestMapping("/useroverview")
    public String getAccountUsers(Model model) {
        if (AccountUser.currentLoggedAccount != null) {
            String accountUserName = AccountUser.currentLoggedAccount.getName();
            model.addAttribute("accountUserName", accountUserName);
            return "useroverview/accountView";
        } else {
            return "redirect:";
        }
    }
}
