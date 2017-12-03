package com.aubg.auction.services;

import com.aubg.auction.models.Auction;

import java.util.Date;
import java.util.List;

public interface AuctionService {

    List<Auction> getItemsOnSale();
    List<Date> getStartDatesForItemsOnSale();

}
