package com.tzamastil.onlineBankovnictviApp.controllers;
import com.tzamastil.onlineBankovnictviApp.databaseModel.AccountUser;
import com.tzamastil.onlineBankovnictviApp.databaseModel.Transaction;
import com.tzamastil.onlineBankovnictviApp.repos.TransactionRepo;
import com.tzamastil.onlineBankovnictviApp.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AccountViewController {

    private final TransactionRepo transactionRepo;
    private final UserRepo userRepo;

    public AccountViewController(TransactionRepo transactionRepo, UserRepo userRepo) {
        this.transactionRepo = transactionRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/useroverview")
    @Transactional
    public String transferMoney(@RequestParam long accountNumber, @RequestParam double amount) {
        AccountUser origin = AccountUser.currentLoggedAccount;
        AccountUser recipient = null;
        for (AccountUser accountUser : userRepo.findAll()) {
            if (accountUser.getAccountNumber() == accountNumber) {
                recipient = accountUser;
            }
        }
        if (origin.getBalance() >= amount && recipient != null) {
            origin.setBalance(origin.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
            processTransaction(origin, recipient, amount);
        }
        return "redirect:/useroverview";
    }

    @GetMapping("/useroverview")
    public String getAccountUsers(Model model) {
        if (AccountUser.currentLoggedAccount != null) {
            AccountUser currentAccount = AccountUser.currentLoggedAccount;
            String accountUserName = currentAccount.getName();
            Double balance = currentAccount.getBalance();
            model.addAttribute("accountUserName", accountUserName);
            model.addAttribute("Balance", balance);
            model.addAttribute("IncomingTransactionList",
                    currentAccount.getIncomingTransactions(transactionRepo));
            model.addAttribute("OutgoingTransactionList",
                    currentAccount.getOutgoingTransactions(transactionRepo));
            return "useroverview/accountView";
        } else {
            return "redirect:";
        }
    }


    public void processTransaction(AccountUser origin, AccountUser recipient, Double amount) {
        Transaction transaction = new Transaction(origin, recipient,amount);
        transactionRepo.save(transaction);
    }
}
