package com.aubg.auction.services;

import com.aubg.auction.models.Auction;
import com.aubg.auction.models.User;

import java.util.Date;
import java.util.List;

public interface AuctionService {
    List<Auction> getItemsOnSale();
    List<Date> getStartDatesForItemsOnSale();
    void updateAuctionPrice(Long auctionId, Double price);
    void updateAuctionUser(Long auctionId, User user);
}
