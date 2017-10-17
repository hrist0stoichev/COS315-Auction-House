package com.aubg.auction.services;

import com.aubg.auction.models.User;

public interface RegistrationService {

    public void create(User user);
    public boolean exists(User user);
}
