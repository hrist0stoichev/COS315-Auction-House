package com.aubg.auction.controllers;

import com.aubg.auction.models.Auction;
import com.aubg.auction.services.AccountService;
import com.aubg.auction.services.CategorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    List<Auction> categorySearch(@PathVariable String categoryName) {
        return categorySearchService.getAuctionsByCategory(categoryName);
    }

    @GetMapping("/catalog")
    public String catalog(){
        return "categorysearchpage";
    }



    @GetMapping(value = "/category/")
    public @ResponseBody
    List<Auction> categories() {
        return categorySearchService.getAllAuctions();
    }
}
