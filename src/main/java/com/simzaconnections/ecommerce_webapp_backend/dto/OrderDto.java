package com.simzaconnections.ecommerce_webapp_backend.dto;

import lombok.Data;
import java.util.ArrayList;

@Data
public class OrderDto {
    private Integer order_id;

    private String token;
    private Double totalPrice;
    private ArrayList<Integer> prodIDs;
    private ArrayList<Integer> quantities;
    private String country;
    private String province;
    private String subrub;
    private String city;
    private String streetName;
    private Integer areaCode;

}
