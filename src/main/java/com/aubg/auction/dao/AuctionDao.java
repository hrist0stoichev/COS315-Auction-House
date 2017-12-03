package com.aubg.auction.dao;

import com.aubg.auction.models.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AuctionDao extends JpaRepository<Auction,Long> {

    List<Auction> getAllByStartDate(Date startDate);
    List<Auction> getAllByOnSale(boolean onSale);
    List<Auction> getAllByIsSold(boolean isSold);

    @Query("select distinct a.startDate from Auction a where a.onSale=true")
    List<Date> getStartDateWhereOnSale();


}
