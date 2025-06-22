package com.simzaconnections.ecommerce_webapp_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "Customer_Orders")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public CustomerOrder(String token, Double totalPrice, ArrayList<Integer> prodIDs, ArrayList<Integer> quantities, String country, String province, String subrub, String city, String streetName, Integer areaCode) {
        this.token = token;
        this.totalPrice = totalPrice;
        this.prodIDs = prodIDs;
        this.quantities = quantities;
        this.country = country;
        this.province = province;
        this.subrub = subrub;
        this.city = city;
        this.streetName = streetName;
        this.areaCode = areaCode;
    }

    public CustomerOrder() {

    }
}
