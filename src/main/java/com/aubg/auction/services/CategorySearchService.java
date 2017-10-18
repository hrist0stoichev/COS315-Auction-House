package com.aubg.auction.services;

import com.aubg.auction.models.Auction;

import java.util.List;

public interface CategorySearchService {
    List<Auction> getAllAuctions();
    List<Auction> getAuctionsByCategory(String category);
}
