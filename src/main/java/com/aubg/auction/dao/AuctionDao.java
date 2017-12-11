package com.aubg.auction.dao;

import com.aubg.auction.models.Auction;
import com.aubg.auction.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AuctionDao extends JpaRepository<Auction,Long> {

    List<Auction> getAllByStartDateAndIsApproved(Date startDate, boolean approved);
    List<Auction> getAllByOnSale(boolean onSale);
    List<Auction> getAllByIsSold(boolean isSold);
    List<Auction> getAllByIsApproved(boolean approved);
    List<Auction> getAllByIsSoldAndHighestBidUserAndIsPaid(boolean isSold, User highestBidUser, boolean isPaid);

    @Query("select distinct a.startDate from Auction a where a.onSale=true")
    List<Date> getStartDateWhereOnSale();

    @Modifying
    @Query("UPDATE Auction a SET a.price = ?2 WHERE a.id = ?1")
    void updateAuctionPrice(Long auctionId, Double price);
}
