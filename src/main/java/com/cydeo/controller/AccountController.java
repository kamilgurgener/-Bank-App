package com.cydeo.controller;

import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/index")
    public String getIndex(Model model){

        model.addAttribute("accountList", accountService.listAllAccount() );

        return "account/index";

    }

    @GetMapping("/create-form")
    public String createNewAccount(Model model){


        //empty account object provided
        model.addAttribute("account", new Account());
        //account type enum needs to fill dropdown
        List <AccountType> accountTypeList = Arrays.asList(AccountType.SAVING, AccountType.CHECKING);
        model.addAttribute("accountTypeList", accountTypeList);
        return "account/create-account";
    }

    //create method to capture information from UI,
    @PostMapping("/account/confirm")
    public String getInformationFromUI(@ModelAttribute("account") Account account, Model model){

        //print them on the console.
        System.out.println(account);

        //trigger createAccount method, create the account based on user input
        model.addAttribute("account", accountService.createNewAccount(account.getBalance(),
                new Date(),account.getAccountType(), account.getUserId()));

        return "redirect:/index";
    }

      @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") UUID id){
          System.out.println(id);
          accountService.deleteAccount(id);
          //trigger deleteAccount method.
        return "redirect:/index";
      }

      @GetMapping("/activate/{id}")
    public String activateAccount(@PathVariable("id") UUID id){

        accountService.activateAccount(id);
        return "redirect:/index";
      }


}









