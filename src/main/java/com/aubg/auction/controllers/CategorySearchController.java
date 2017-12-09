package com.aubg.auction.controllers;

import com.aubg.auction.dao.AuctionDao;
import com.aubg.auction.dao.CategoryDAO;
import com.aubg.auction.models.Auction;
import com.aubg.auction.models.Category;
import com.aubg.auction.services.AuctionService;
import com.aubg.auction.services.CategorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@SessionAttributes("currentUserId")
public class CategorySearchController {
    private final CategorySearchService categorySearchService;
    private final AuctionDao auctionDao;
    private final CategoryDAO categoryDAO;


    @Autowired
    public CategorySearchController(CategorySearchService categorySearchService, AuctionService auctionService, AuctionDao auctionDao, CategoryDAO categoryDAO) {
        this.categorySearchService = categorySearchService;
        this.auctionDao = auctionDao;
        this.categoryDAO = categoryDAO;
    }

    @GetMapping(value = "/category/{categoryName}")
    public @ResponseBody
    Set<Auction> categorySearch(@PathVariable String categoryName) {
        Set<Auction> auctions = categorySearchService.getAuctionsByCategory(categoryName);
        if (auctions != null) {
            return auctions;
        } else return new HashSet<>();
    }

    @GetMapping("/catalog")
    public String catalog() {
        return "auctionspage";
    }

    @GetMapping("/categories")
    public String categories() {
        return "categoriespage";
    }

    @GetMapping(value = "/category/")
    public @ResponseBody
    List<Auction> getAllAuctions() {
        return auctionDao.getAllByIsApproved(true);
    }


    @GetMapping("/categories/")
    public @ResponseBody
    List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }
}
