package com.aubg.auction.services;

import com.aubg.auction.models.Auction;

public interface AdminService {

    void addNewItem(Auction auction,String categoryName);
}
