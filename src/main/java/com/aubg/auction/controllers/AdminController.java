package com.aubg.auction.controllers;

import com.aubg.auction.models.Auction;
import com.aubg.auction.models.Category;
import com.aubg.auction.services.AdminService;
import com.aubg.auction.services.CategorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"currentUserId", "isAdmin"})
public class AdminController {

    private final AdminService adminService;
    private final CategorySearchService categoryService;

    @Autowired
    public AdminController(AdminService adminService, CategorySearchService categoryService) {
        this.adminService = adminService;
        this.categoryService = categoryService;
    }

    @GetMapping("/addAuction")
    public String addAuction(Model model) {
        if (!model.containsAttribute("isAdmin")) {
            return "errorNotAuthorized";
        }

        return "formAddItem";
    }

    @PostMapping("/addAuction")
    public String addAuction(Model model, @RequestParam(name = "category_name") String categoryName,
                             @ModelAttribute Auction auction) {
        if (categoryService.categoryExists(categoryName)) {
            adminService.addNewItem(auction, categoryName);
            return "successAdded";
        }

        return "categoryNotExisting";
    }

    @GetMapping("/deleteAuction")
    public String deleteAuction(Model model) {
        if (!model.containsAttribute("isAdmin")) {
            return "errorNotAuthorized";
        }

        return "formDeleteItem";
    }

    @PostMapping("/deleteAuction")
    public String deleteAuction(Model model, @RequestParam(name = "auction_id") int auctionId) {
        if (!model.containsAttribute("isAdmin")) {
            return "errorNotAuthorized";
        }

        adminService.deleteAuctionById(auctionId);
        return "successDeleted";
    }

    @GetMapping("/addCategory")
    public String addCategory(Model model) {
        if (!model.containsAttribute("isAdmin")) {
            return "errorNotAuthorized";
        }

        return "formAddCategory";
    }

    @PostMapping("/addCategory")
    public String addAuction(Model model, @RequestParam(name = "category_name") String categoryName) {
        if (!model.containsAttribute("isAdmin")) {
            return "errorNotAuthorized";
        }

        Category category = new Category();
        category.setName(categoryName);
        adminService.addNewCategory(category);
        return "successAdded";
    }
}
