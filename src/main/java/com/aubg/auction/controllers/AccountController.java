package com.aubg.auction.controllers;

import com.aubg.auction.models.User;
import com.aubg.auction.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@SessionAttributes("currentUserId")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "loginform";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user) {
        if (accountService.existUsername(user.getUsername())) {
            return "usernameexists";
        }
        accountService.createUser(user);
        return "successRegister";
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password) {
        User user = this.accountService.getUserByUsername(username);

        if (!this.accountService.isPasswordCorrect(user, password)) {
            return "errorlogin";
        }

        model.addAttribute("username", username);
        model.addAttribute("currentUserId", user.getId());
        return "biddingpage";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("currentUserId", -1);
        return "loginform";
    }

    @GetMapping("/asd")
    public String asd(Model model) {

        String currentUserId = model.asMap().getOrDefault("currentUserId", "-1").toString();

        if(Objects.equals(currentUserId, "-1")) {
            return "errorlogin";
        }

        return "successRegister";
    }
}