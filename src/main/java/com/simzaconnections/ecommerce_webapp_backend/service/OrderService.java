package com.simzaconnections.ecommerce_webapp_backend.service;

import com.simzaconnections.ecommerce_webapp_backend.dto.OrderDto;
import com.simzaconnections.ecommerce_webapp_backend.model.CustomerOrder;
import com.simzaconnections.ecommerce_webapp_backend.repository.OrderRepository;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

@Service
public class OrderService {


    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto addOrder(OrderDto orderDto){
        CustomerOrder customerOrder = new CustomerOrder(orderDto.getToken(), orderDto.getTotalPrice(), orderDto.getProdIDs(), orderDto.getQuantities(), orderDto.getCountry(), orderDto.getProvince(), orderDto.getSubrub(), orderDto.getCity(), orderDto.getStreetName(), orderDto.getAreaCode());
        CustomerOrder savedOrder = orderRepository.save(customerOrder);
        return convertToDto(savedOrder);
    }

    private OrderDto convertToDto(CustomerOrder customerOrder) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrder_id(customerOrder.getOrder_id());
        orderDto.setToken(customerOrder.getToken());
        orderDto.setTotalPrice(customerOrder.getTotalPrice());
        orderDto.setProdIDs(customerOrder.getProdIDs());
        orderDto.setQuantities(customerOrder.getQuantities());
        orderDto.setCountry(customerOrder.getCountry());
        orderDto.setProvince(customerOrder.getProvince());
        orderDto.setSubrub(customerOrder.getSubrub());
        orderDto.setCity(customerOrder.getCity());
        orderDto.setStreetName(customerOrder.getStreetName());
        orderDto.setAreaCode(customerOrder.getAreaCode());
        return orderDto;
    }

}
