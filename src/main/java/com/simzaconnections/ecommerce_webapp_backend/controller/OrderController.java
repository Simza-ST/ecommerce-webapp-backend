package com.simzaconnections.ecommerce_webapp_backend.controller;

import com.simzaconnections.ecommerce_webapp_backend.dto.OrderDto;
import com.simzaconnections.ecommerce_webapp_backend.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:5173/")
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Checkout session checkout api

    @PostMapping("/create-checkout-session")
    public OrderDto checkoutList(@RequestBody OrderDto orderDto){
        return orderService.addOrder(orderDto);

    }

}
