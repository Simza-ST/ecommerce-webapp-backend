package com.simzaconnections.ecommerce_webapp_backend.repository;

import com.simzaconnections.ecommerce_webapp_backend.model.Cart;
import com.simzaconnections.ecommerce_webapp_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
