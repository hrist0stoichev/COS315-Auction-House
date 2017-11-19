package com.aubg.auction.controllers;

import com.aubg.auction.models.Auction;
import com.aubg.auction.models.Category;
import com.aubg.auction.models.User;
import com.aubg.auction.services.AccountService;
import com.aubg.auction.services.AdminService;
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
public class AdminController {

    private final AdminService adminService;
    private final CategorySearchService categoryService;
    private final AccountService accountService;

    @Autowired
    public AdminController(AdminService adminService, CategorySearchService categoryService, AccountService accountService) {
        this.adminService = adminService;
        this.categoryService = categoryService;
        this.accountService = accountService;
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
                             @RequestParam("name") String name,@RequestParam("price") Long price,
                             @RequestParam("start_date")String start_date,
                             @RequestParam("end_date")String end_date,
                             @RequestParam("image") String image){

        if (categoryService.categoryExists(categoryName)) {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start = null;
            Date end=null;
            try {
                start = sdf.parse(start_date);
                end =sdf.parse(end_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Auction auction =new Auction(name,price,start,end,image);


            adminService.addNewItem(auction, categoryName);
            return "successAdded";
        }

        return "categoryNotExisting";
    }


    @GetMapping("/addCategory")
    public String addCategory(Model model) {
        if (!model.containsAttribute("isAdmin")) {
            return "errorNotAuthorized";
        }

        return "formAddCategory";
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

    @GetMapping("/allUsers")
    public String allUsers(Model model) {
        if (!model.containsAttribute("isAdmin")) {
            return "errorNotAuthorized";
        }

        List<User> users = this.accountService.getAllUsers();
        model.addAttribute("users", users);
        return "allUsers";
    }

    @GetMapping("/allUsers/{userId}")
    public String userDetails(Model model, @PathVariable String userId) {
        if (!model.containsAttribute("isAdmin")) {
            return "errorNotAuthorized";
        }

        User user = this.accountService.getUserById(Long.parseLong(userId));
        model.addAttribute("user", user);

        return "userDetails";
    }
}
