package com.simzaconnections.ecommerce_webapp_backend.dto.cart;

import com.simzaconnections.ecommerce_webapp_backend.model.Product;
import lombok.Data;

@Data
public class CartItemDto {
    private Integer id;
    private Integer quantity;
    private Product product;

    public CartItemDto(Integer quantity, Product product, Integer id) {
        this.quantity = quantity;
        this.product = product;
        this.id = id;
    }
}
