package com.simzaconnections.ecommerce_webapp_backend.dto.cart;

import lombok.Data;
import java.util.List;

@Data
public class CartDto {
    private List<CartItemDto> cartItems;
    private double totalCost;
}
