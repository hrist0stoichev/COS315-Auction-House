package com.aubg.auction.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aubg.auction.models.User;

public interface UserDAO extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
}
