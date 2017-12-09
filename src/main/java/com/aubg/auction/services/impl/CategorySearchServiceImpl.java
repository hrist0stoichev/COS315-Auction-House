package com.aubg.auction.services.impl;

import com.aubg.auction.dao.AuctionDao;
import com.aubg.auction.dao.CategoryDAO;
import com.aubg.auction.models.Auction;
import com.aubg.auction.models.Category;
import com.aubg.auction.services.CategorySearchService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public Set<Auction> getAuctionsByCategory(String categoryName) {
        Category category = categoryDAO.getCategoryByName(categoryName);
        if(category == null){
            return new HashSet<>();
        }

        else{

            Set<Auction> auctions = category.getAuctions().stream()
                    .filter(a -> a.getIsApproved() == true).collect(Collectors.toSet());

            return auctions;

        }


    }

    @Override
    public boolean categoryExists(String name) {
        return categoryDAO.getCategoryByName(name) != null;
    }
}
