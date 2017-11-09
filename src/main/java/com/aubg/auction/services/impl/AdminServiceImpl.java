package com.aubg.auction.services.impl;

import com.aubg.auction.dao.AuctionDao;
import com.aubg.auction.dao.CategoryDAO;
import com.aubg.auction.models.Auction;
import com.aubg.auction.models.Category;
import com.aubg.auction.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    private final AuctionDao auctionDao;
    private final CategoryDAO categoryDAO;

    @Autowired
    public AdminServiceImpl(AuctionDao auctionDao, CategoryDAO categoryDAO) {
        this.auctionDao = auctionDao;
        this.categoryDAO = categoryDAO;
    }

    @Override
    public void addNewItem(Auction auction, String categoryName) {
        Category category = categoryDAO.getCategoryByName(categoryName);
        auctionDao.save(auction);
        category.getAuctions().add(auction);
    }

    @Override
    public void addNewCategory(Category category) {
        categoryDAO.save(category);
    }

    @Override
    public void deleteAuctionById(long auctionId) {
        auctionDao.delete(auctionId);
    }
}
