package com.aubg.auction.controllers;

import com.aubg.auction.models.Auction;
import com.aubg.auction.services.AccountService;
import com.aubg.auction.services.CategorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@SessionAttributes("currentUserId")
public class CategorySearchController {
    private final CategorySearchService categorySearchService;


    @Autowired
    public CategorySearchController(CategorySearchService categorySearchService) {
        this.categorySearchService = categorySearchService;
    }

    @GetMapping(value = "/category/{categoryName}")
    public @ResponseBody
    Set<Auction> categorySearch(@PathVariable String categoryName) {
        return categorySearchService.getAuctionsByCategory(categoryName);
    }

    @GetMapping("/catalog")
    public String catalog(Model model){
        List<Auction> auctions = categorySearchService.getAllAuctions();
        model.addAttribute("auctions",auctions);

        return "categorysearchpage";
    }



    @GetMapping(value = "/category/")
    public @ResponseBody
    List<Auction> categories() {
        return categorySearchService.getAllAuctions();
    }
}
