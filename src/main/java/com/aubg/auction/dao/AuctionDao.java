package com.aubg.auction.dao;

import com.aubg.auction.models.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionDao extends JpaRepository<Auction,Long> {
    List<Auction> findAuctionsByCategory(String category);
}
