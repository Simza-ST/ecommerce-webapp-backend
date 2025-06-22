package com.simzaconnections.ecommerce_webapp_backend.dto.checkout;

import lombok.Data;

@Data
public class CheckoutItemDto {

    private String productName;
    private int quantity;
    private double price;
    private long productId;
    private int userId;
}
