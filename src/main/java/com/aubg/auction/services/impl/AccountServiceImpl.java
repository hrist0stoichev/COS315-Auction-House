package com.aubg.auction.services.impl;

import com.aubg.auction.models.User;
import com.aubg.auction.dao.UserDAO;
import com.aubg.auction.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final UserDAO userDAO;

    @Autowired
    public AccountServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserByUsername(String username) {
        return this.userDAO.findUserByUsername(username);
    }

    public boolean existUsername(String username) {
        return this.getUserByUsername(username) != null;
    }

    public void createUser(User user) {

        user.setAdmin(false);
        userDAO.save(user);
    }

    public boolean isPasswordCorrect(User user, String password) {
        return user != null && user.getPassword().equals(password);
    }

    public User getUserById(long userId) {
        return this.userDAO.findOne(userId);
    }
}
