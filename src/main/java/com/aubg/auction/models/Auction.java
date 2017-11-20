package com.aubg.auction.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "auction")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    @Column(nullable = false)
    private String image;

    public Auction() {}

    public Auction(String name, Double price, Date startDate, Date endDate, String image) {
        this.name = name;
        this.price = price;
        this.startDate=startDate;
        this.endDate=endDate;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getImage() { return image; }

    public void setImage(String image) {
        this.image = image;
    }
}

