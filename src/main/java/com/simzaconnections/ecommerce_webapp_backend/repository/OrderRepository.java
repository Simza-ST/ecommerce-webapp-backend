package com.simzaconnections.ecommerce_webapp_backend.repository;

import com.simzaconnections.ecommerce_webapp_backend.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Integer> {
}
