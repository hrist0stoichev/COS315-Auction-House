package com.aubg.auction.controllers;

import com.aubg.auction.services.impl.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.aubg.auction.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/bid")
public class BiddingController {

    private final LogInService logInService;


    @Autowired
    public BiddingController(LogInService logInService) {
        this.logInService = logInService;
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "loginform";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(Model model, @RequestParam(name="username")String username,
                        @RequestParam(name="password")String password){

        User user =new User();
        user.setUsername(username);
        user.setPassword(password);

        if(logInService.isRegistered(user)){
            model.addAttribute("username",username);
            return "biddingpage";

        }

        else{
            return "errorlogin";
        }



    }


}
