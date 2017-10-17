package com.aubg.auction.services.impl;

import com.aubg.auction.dao.UserDAO;
import com.aubg.auction.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aubg.auction.models.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private final UserDAO userDAO;

    @Autowired
    public RegistrationServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean exists(User user){

        List<User> users = userDAO.findAll();


        for(User u:users){
            if(user.getUsername().equals(u.getUsername())){

                return true;

            }
        }

        return false;
    }

    public void create(User user){

        userDAO.save(user);
    }
}
