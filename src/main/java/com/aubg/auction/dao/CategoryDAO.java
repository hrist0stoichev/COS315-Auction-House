package com.aubg.auction.dao;

import com.aubg.auction.models.Auction;
import com.aubg.auction.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDAO extends JpaRepository<Category,Long> {
    Category getCategoryByName(String name);
}
