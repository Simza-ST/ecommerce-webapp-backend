package com.simzaconnections.ecommerce_webapp_backend.dto;

import com.simzaconnections.ecommerce_webapp_backend.model.User;
import lombok.Data;

@Data
public class ResponseDto {

    private String status;
    private String message;
    private User user;

    public ResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
