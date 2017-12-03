package com.aubg.auction.controllers;

import com.aubg.auction.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    private final AuctionService auctionService;

    @Autowired
    public HomeController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/")
    public String home(Model model){

        List<Date> startDatesForItemsOnSale = auctionService.getStartDatesForItemsOnSale();
        model.addAttribute("startDates",startDatesForItemsOnSale);

        return "home";
    }
}
