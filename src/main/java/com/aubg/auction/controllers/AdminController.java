package com.aubg.auction.controllers;

import com.aubg.auction.models.Auction;
import com.aubg.auction.models.Category;
import com.aubg.auction.models.User;
import com.aubg.auction.services.AccountService;
import com.aubg.auction.services.AdminService;
import com.aubg.auction.services.AuctionService;
import com.aubg.auction.services.CategorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"currentUserId", "isAdmin"})
public class AdminController {

    private final AdminService adminService;
    private final CategorySearchService categoryService;
    private final AccountService accountService;
    private final AuctionService auctionService;

    @Autowired
    public AdminController(AdminService adminService, CategorySearchService categoryService, AccountService accountService, AuctionService auctionService) {
        this.adminService = adminService;
        this.categoryService = categoryService;
        this.accountService = accountService;
        this.auctionService = auctionService;
    }

    @GetMapping("/addAuction")
    public String addAuction(Model model) {
        if (!model.containsAttribute("isAdmin") || (int) model.asMap().get("isAdmin") != 1) {
            return "errorNotAuthorized";
        }

        return "formAddItem";
    }

    @PostMapping("/addAuction")
    public String addAuction(Model model, @RequestParam(name = "category_name") String category_name,
                             @RequestParam("name") String name, @RequestParam("price") Double price,
                             @RequestParam("start_date") String start_date,
                             @RequestParam("end_date") String end_date,
                             @RequestParam("image") String image) {

        if (categoryService.categoryExists(category_name)) {
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
                return "redirect:/addAuction";
            }

            Auction auction = new Auction(name, price, start, end, image);
            auction.setIsApproved(true);
            auctionService.addNewItem(auction, category_name);
            return "successAdded";
        }
        
            return "categoryNotExisting";

    }




    @GetMapping("/addCategory")
    public String addCategory(Model model) {
        if (!model.containsAttribute("isAdmin") || (int) model.asMap().get("isAdmin") != 1) {
            return "errorNotAuthorized";
        }

        return "formAddCategory";
    }

    @GetMapping("/soldAuctions/")
    public @ResponseBody
    List<Auction> getAllSoldAuctions() {

        return this.adminService.getSoldAuctions();

    }

    @GetMapping("/deleteAuction")
    public String deleteAuction(Model model) {
        if (!model.containsAttribute("isAdmin") || (int) model.asMap().get("isAdmin") != 1) {
            return "errorNotAuthorized";
        }

        return "formDeleteItem";
    }

    @PostMapping("/deleteAuction")
    public String deleteAuction(Model model, @RequestParam(name = "auction_id") int auctionId) {
        if (!model.containsAttribute("isAdmin") || (int) model.asMap().get("isAdmin") != 1) {
            return "errorNotAuthorized";
        }

        adminService.deleteAuctionById(auctionId);
        return "successDeleted";
    }


    @PostMapping("/addCategory")
    public String addAuction(Model model, @RequestParam(name = "category_name") String categoryName) {
        if (!model.containsAttribute("isAdmin") || (int) model.asMap().get("isAdmin") != 1) {
            return "errorNotAuthorized";
        }


        Category category = new Category();
        category.setName(categoryName);
        adminService.addNewCategory(category);
        return "successAdded";
    }

    @GetMapping("/allUsers")
    public String allUsers(Model model) {
        if (!model.containsAttribute("isAdmin") || (int) model.asMap().get("isAdmin") != 1) {
            return "errorNotAuthorized";
        }

        List<User> users = this.accountService.getAllUsers();
        model.addAttribute("users", users);
        return "allUsers";
    }

    @GetMapping("/allUsers/{userId}")
    public String userDetails(Model model, @PathVariable String userId) {
        if (!model.containsAttribute("isAdmin") || (int) model.asMap().get("isAdmin") != 1) {
            return "errorNotAuthorized";
        }

        User user = this.accountService.getUserById(Long.parseLong(userId));
        model.addAttribute("user", user);

        return "userDetails";
    }

    @GetMapping("/startAuction")
    public String startAuction(Model model) {

        if (!model.containsAttribute("isAdmin") || (int) model.asMap().get("isAdmin") != 1) {
            return "errorNotAuthorized";
        } else {
            Map<Date, List<Auction>> auctionsByDate = adminService.auctionsGroupedByStartDate();
            model.addAttribute("auctionsByDate", auctionsByDate);

            return "startAuction";

        }


    }

    @GetMapping("/startAuction/{startDate}")
    public String startAuction(Model model, @PathVariable String startDate) {
        if (!model.containsAttribute("isAdmin") || (int) model.asMap().get("isAdmin") != 1) {
            return "errorNotAuthorized";
        }

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Auction> auctionsByStartDate = adminService.getAuctionsByStartDate(date);
        auctionsByStartDate.forEach((auction) -> auction.setOnSale(true));
        adminService.saveChanges(auctionsByStartDate);
        model.addAttribute("startDate", date);


        return "successStartAuction";
    }

    @GetMapping("/approveAuctions")
    public String approveSuggestedAuctions(Model model){

        if (!model.containsAttribute("isAdmin") || (int) model.asMap().get("isAdmin") != 1) {
            return "errorNotAuthorized";
        }

        List<Auction> suggestedByUsersAuctions = adminService.getSuggestedByUsersAuctions();
        model.addAttribute("suggestedByUsersAuctions",suggestedByUsersAuctions);


        return "suggestedAuctions";
    }



    @PostMapping("/approveAuctions")
    public String approveSuggestedAuctions(@RequestParam(name = "ids",required = false)
                                           Long[] ids){
        if(ids!=null){

            List<Auction> approvedAuctions=new ArrayList<>();

            for(Long id: ids){

                Auction auctionById = auctionService.findAuctionById(id);
                auctionById.setIsApproved(true);
                approvedAuctions.add(auctionById);


            }

            adminService.saveChanges(approvedAuctions);
        }


        return "successApproved";

    }

    @PostMapping("/bid")
    public String bid
            (@RequestParam(name = "ids", required = false)
                     Long[] ids) {
        {
            if (ids != null) {

                List<Auction> soldAuctions = new ArrayList<>();

                for (Long id : ids) {

                    Auction auctionById = auctionService.findAuctionById(id);
                    auctionById.setIsSold(true);
                    soldAuctions.add(auctionById);

                }


                adminService.saveChanges(soldAuctions);


            }

            return "adminPage";

        }

    }



}


