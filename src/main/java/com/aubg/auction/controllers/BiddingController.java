package com.aubg.auction.controllers;

import com.aubg.auction.models.Auction;
import com.aubg.auction.models.User;
import com.aubg.auction.services.AccountService;
import com.aubg.auction.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes({"currentUserId", "isAdmin"})
public class BiddingController {
    private final AuctionService auctionService;
    private final AccountService accountService;
    @Autowired
    public BiddingController(AuctionService auctionService, AccountService accountService) {
        this.auctionService = auctionService;
        this.accountService = accountService;
    }


    @GetMapping("/bid")
    public String bid(Model model) {
        if (!model.containsAttribute("currentUserId")) {
            return "redirect:/login";
        }

        List<Auction> itemsOnSale = auctionService.getItemsOnSale();
        model.addAttribute("itemsOnSale", itemsOnSale);
        return "biddingpage";
    }

    @PostMapping("/bid/{auctionId}/{newAuctionPrice}")
    public @ResponseBody
    void updateAuctionPrice(Model model, @PathVariable Long auctionId, @PathVariable Double newAuctionPrice) {
        auctionService.updateAuctionPrice(auctionId, newAuctionPrice);
        Long userId = (Long) model.asMap().get("currentUserId");
        User user = accountService.getUserById(userId);
        auctionService.updateAuctionUser(auctionId, user);
    }
}
