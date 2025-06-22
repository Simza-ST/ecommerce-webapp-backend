package com.simzaconnections.ecommerce_webapp_backend.repository;

import com.simzaconnections.ecommerce_webapp_backend.model.AuthenticationToken;
import com.simzaconnections.ecommerce_webapp_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {

    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);
}
