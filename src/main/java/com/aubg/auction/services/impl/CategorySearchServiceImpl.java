package com.aubg.auction.services.impl;

import com.aubg.auction.dao.AuctionDao;
import com.aubg.auction.dao.CategoryDAO;
import com.aubg.auction.models.Auction;
import com.aubg.auction.services.CategorySearchService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CategorySearchServiceImpl implements CategorySearchService {

    private final AuctionDao auctionDao;
    private final CategoryDAO categoryDAO;

    @Autowired
    public CategorySearchServiceImpl(AuctionDao auctionDao, CategoryDAO categoryDAO) {
        this.auctionDao = auctionDao;
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Auction> getAllAuctions() {
        return auctionDao.findAll();
    }

    @Override
    public Set<Auction> getAuctionsByCategory(String category) {
        return categoryDAO.getCategoryByName(category).getAuctions();
    }

    @Override
    public boolean categoryExists(String name) {
        return categoryDAO.getCategoryByName(name) != null;
    }
}
