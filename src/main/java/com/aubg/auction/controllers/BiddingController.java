package com.aubg.auction.controllers;

import com.aubg.auction.models.Auction;
import com.aubg.auction.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"currentUserId", "isAdmin"})
public class BiddingController {

    private final AuctionService auctionService;

    @Autowired
    public BiddingController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }


    @GetMapping("/bid")
    public String bid(Model model){
        if(!model.containsAttribute("currentUserId")){
            return "redirect:/login";
        }

        List<Auction> itemsOnSale = auctionService.getItemsOnSale();
        model.addAttribute("itemsOnSale",itemsOnSale);
        return "biddingpage";
    }

}
