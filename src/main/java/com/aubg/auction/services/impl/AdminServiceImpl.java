package com.aubg.auction.services.impl;

import com.aubg.auction.dao.AuctionDao;
import com.aubg.auction.dao.CategoryDAO;
import com.aubg.auction.models.Auction;
import com.aubg.auction.models.Category;
import com.aubg.auction.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public Map<Date, List<Auction>> auctionsGroupedByStartDate() {

       return this.auctionDao.findAll().stream()
                .collect(Collectors.groupingBy(a->a.getStartDate()));
    }

    @Override
    public List<Auction> getAuctionsByStartDate(Date date) {
        return this.auctionDao.getAllByStartDate(date);
    }

    @Override
    public List<Auction> getSoldAuctions() {
        return this.auctionDao.getAllByIsSold(true);
    }

    @Override
    public void saveChanges(List<Auction> auctions) {
        this.auctionDao.save(auctions);
    }


}
