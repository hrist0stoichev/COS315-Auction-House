package com.aubg.auction.services.impl;

import com.aubg.auction.dao.AuctionDao;
import com.aubg.auction.models.Auction;
import com.aubg.auction.services.CategorySearchService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategorySearchServiceImpl implements CategorySearchService {

    private final AuctionDao auctionDao;

    @Autowired
    public CategorySearchServiceImpl(AuctionDao auctionDao) {
        this.auctionDao = auctionDao;
    }

    @Override
    public List<Auction> getAllAuctions() {
        return auctionDao.findAll();
    }

    public List<Auction> getAuctionsByCategory(String category) {
        return auctionDao.findAuctionsByCategory(category);
    }
}
