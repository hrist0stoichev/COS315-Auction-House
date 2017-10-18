package com.aubg.auction.controllers;

import com.aubg.auction.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("currentUserId")
public class BiddingController {

    @Autowired
    public BiddingController(){}

    @PostMapping(path = {"/login"}, params = {"categorysearch"})
    public String redirectToCategorySearch() {
        return "categorysearchpage";
    }
}
