package com.aubg.auction.services;

import com.aubg.auction.models.Auction;
import com.aubg.auction.models.Category;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AdminService {

    void addNewCategory(Category category);

    void deleteAuctionById(long auctionId);

    Map<Date,List<Auction>> auctionsGroupedByStartDate();

    List<Auction> getAuctionsByStartDate(Date date);

    List<Auction> getSoldAuctions();
    List<Auction> getSuggestedByUsersAuctions();


    void saveChanges(List<Auction> auctions);





}
