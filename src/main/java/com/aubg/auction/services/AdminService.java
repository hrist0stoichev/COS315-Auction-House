package com.aubg.auction.services;

import com.aubg.auction.models.Auction;
import com.aubg.auction.models.Category;

public interface AdminService {
    void addNewItem(Auction auction,String categoryName);
    void addNewCategory(Category category);
    void deleteAuctionById(long auctionId);
}
