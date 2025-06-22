package com.simzaconnections.ecommerce_webapp_backend.service;

import com.simzaconnections.ecommerce_webapp_backend.dto.ProductDto;
import com.simzaconnections.ecommerce_webapp_backend.model.User;
import com.simzaconnections.ecommerce_webapp_backend.model.WishList;
import com.simzaconnections.ecommerce_webapp_backend.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {


    private final WishListRepository wishListRepository;


    private final ProductService productService;

    public WishListService(WishListRepository wishListRepository, ProductService productService) {
        this.wishListRepository = wishListRepository;
        this.productService = productService;
    }

    public void createWishlist(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<ProductDto> getWishListForUser(User user) {
        final List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDto> productDtos = new ArrayList<>();
        for (WishList wishList : wishLists) {
            productDtos.add(productService.getProductDto(wishList.getProduct()));
        }

        return productDtos;
    }
}
