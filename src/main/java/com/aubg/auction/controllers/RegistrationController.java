package com.aubg.auction.controllers;

import com.aubg.auction.services.impl.RegistrationService;
import com.aubg.auction.models.User;
import com.aubg.auction.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String register(){

        return "register";

    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(Model model, @RequestParam(name="username")String username,@RequestParam(name="password") String password,
                           @RequestParam(name="first_name") String firstName,
                           @RequestParam(name="last_name")String lastName,
                           @RequestParam(name="email")String email,
                           @RequestParam(name="telephone_number")String telNumber,
                           @RequestParam(name="country")String country,
                           @RequestParam(name="city")String city,
                           @RequestParam(name="street")String street,
                           @RequestParam(name="card_type")String card_type,
                           @RequestParam(name="card_number")String card_number){

        User user=
                new User(username,password,firstName,
                        lastName,email,telNumber,card_type,card_number);
        Address address = new Address(country,city,street);
        user.setAddress(address);

        if(registrationService.exists(user)){
            return "usernameexists";
        }
        else{
            registrationService.create(user);
            model.addAttribute("username",username);
            return "successRegister";
        }



    }


}
