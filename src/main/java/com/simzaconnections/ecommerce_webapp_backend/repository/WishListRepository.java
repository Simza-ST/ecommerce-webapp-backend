package com.simzaconnections.ecommerce_webapp_backend.repository;

import com.simzaconnections.ecommerce_webapp_backend.model.User;
import com.simzaconnections.ecommerce_webapp_backend.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Integer> {
    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}
