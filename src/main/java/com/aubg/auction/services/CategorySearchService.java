package com.aubg.auction.services;

import com.aubg.auction.models.Auction;

import java.util.List;
import java.util.Set;

public interface CategorySearchService {

    List<Auction> getAllAuctions();
    Set<Auction> getAuctionsByCategory(String category);
    boolean categoryExists(String name);
}
