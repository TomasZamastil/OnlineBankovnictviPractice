package com.tzamastil.onlineBankovnictviApp.controllers;
import com.tzamastil.onlineBankovnictviApp.databaseModel.AccountUser;
import com.tzamastil.onlineBankovnictviApp.repos.TransactionRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountViewController {

    private final TransactionRepo transactionRepo;

    public AccountViewController(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @RequestMapping("/useroverview")
    public String getAccountUsers(Model model) {
        if (AccountUser.currentLoggedAccount != null) {
            String accountUserName = AccountUser.currentLoggedAccount.getName();
            model.addAttribute("accountUserName", accountUserName);
            model.addAttribute("transactionList", transactionRepo.findAll());
            return "useroverview/accountView";
        } else {
            return "redirect:";
        }
    }
}
