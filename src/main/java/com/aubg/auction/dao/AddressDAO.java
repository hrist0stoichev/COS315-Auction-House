package com.aubg.auction.dao;

import com.aubg.auction.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDAO extends JpaRepository<Address,Long> {

}
