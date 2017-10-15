package com.aubg.auction.services.impl;
import com.aubg.auction.models.User;
import com.aubg.auction.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LogInService {

    private final UserDAO userDAO;

    @Autowired
    public LogInService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean isRegistered(User user){

        List<User> users = userDAO.findAll();
        for(User u :users){

            if(u.getUsername().equals(user.getUsername())&&u.getPassword().equals(user.getPassword())){
                return true;
            }


        }

        return false;

    }
}
