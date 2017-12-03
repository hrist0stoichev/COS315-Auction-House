package com.aubg.auction.services;

import com.aubg.auction.models.User;

import java.util.List;

public interface AccountService {
    User getUserByUsername(String username);

    boolean existUsername(String username);

    void createUser(User user);

    boolean isPasswordCorrect(User user, String password);

    User getUserById(long userId);

    List<User> getAllUsers();


}
