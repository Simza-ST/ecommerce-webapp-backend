package com.simzaconnections.ecommerce_webapp_backend.dto.checkout;

import lombok.Data;

@Data
public class StripeResponseDto {
    private String sessionId;

    public StripeResponseDto(String sessionId) {
        this.sessionId = sessionId;
    }
}
