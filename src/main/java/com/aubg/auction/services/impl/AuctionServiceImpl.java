package com.aubg.auction.services.impl;

import com.aubg.auction.dao.AuctionDao;
import com.aubg.auction.models.Auction;
import com.aubg.auction.models.User;
import com.aubg.auction.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {

    private final AuctionDao auctionDao;

    @Autowired
    public AuctionServiceImpl(AuctionDao auctionDao) {
        this.auctionDao = auctionDao;
    }

    @Override
    public List<Auction> getItemsOnSale() {
        return this.auctionDao.getAllByOnSale(true);
    }

    @Override
    public List<Date> getStartDatesForItemsOnSale() {
        return this.auctionDao.getStartDateWhereOnSale();
    }

    @Override
    public void updateAuctionPrice(Long auctionId, Double price) {
        Auction a = auctionDao.findOne(auctionId);
        a.setPrice(price);
        this.auctionDao.save(a);
    }

    @Override
    public void updateAuctionUser(Long auctionId, User user) {
        Auction a = auctionDao.findOne(auctionId);
        a.setHighestBidUser(user);
        this.auctionDao.save(a);
    }
}


