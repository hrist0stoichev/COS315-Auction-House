package com.aubg.auction.controllers;

import com.aubg.auction.models.Auction;
import com.aubg.auction.models.User;
import com.aubg.auction.services.AccountService;
import com.aubg.auction.services.AuctionService;
import com.aubg.auction.services.CategorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes({"currentUserId", "isAdmin"})
public class BiddingController {
    private final AuctionService auctionService;
    private final AccountService accountService;
    private final CategorySearchService categoryService;

    @Autowired
    public BiddingController(AuctionService auctionService, AccountService accountService, CategorySearchService categorySearchService) {
        this.auctionService = auctionService;
        this.accountService = accountService;
        this.categoryService = categorySearchService;
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

    @GetMapping("/suggestAuction")
    public String suggestAuction(Model model){
        if (!model.containsAttribute("currentUserId")) {
            return "redirect:/login";
        }

        return "formAddItem";

    }

    @PostMapping("/suggestAuction")
    public String suggestAuction(@RequestParam(name = "category_name") String categoryName,
                                 @RequestParam("name") String name, @RequestParam("price") Double price,
                                 @RequestParam("start_date") String start_date,
                                 @RequestParam("end_date") String end_date,
                                 @RequestParam("image") String image) {

        if (categoryService.categoryExists(categoryName)) {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start = null;
            Date end = null;
            try {
                start = sdf.parse(start_date);
                end = sdf.parse(end_date);
            } catch (ParseException e) {
                System.out.println("unparsable date");

            }

            if (start == null || end == null) {
                return "redirect:/suggestAuction";
            }

            Auction auction = new Auction(name, price, start, end, image);
            auction.setIsApproved(false);
            auctionService.addNewItem(auction,categoryName);

            return "successSuggestion";
        }


        return "redirect:/suggestAuction";





    }
}
