package com.simzaconnections.ecommerce_webapp_backend.repository;

import com.simzaconnections.ecommerce_webapp_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
