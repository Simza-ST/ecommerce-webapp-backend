package com.simzaconnections.ecommerce_webapp_backend.controller;

import com.simzaconnections.ecommerce_webapp_backend.common.ApiResponse;
import com.simzaconnections.ecommerce_webapp_backend.dto.ProductDto;
import com.simzaconnections.ecommerce_webapp_backend.model.Product;
import com.simzaconnections.ecommerce_webapp_backend.model.User;
import com.simzaconnections.ecommerce_webapp_backend.model.WishList;
import com.simzaconnections.ecommerce_webapp_backend.service.AuthenticationService;
import com.simzaconnections.ecommerce_webapp_backend.service.WishListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {


    private final WishListService wishListService;
    private final AuthenticationService authenticationService;

    public WishListController(WishListService wishListService, AuthenticationService authenticationService) {
        this.wishListService = wishListService;
        this.authenticationService = authenticationService;
    }


    // save product as wishlist item
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product,
                                                     @RequestParam("token") String token) {
        // authenticate the token
        authenticationService.authenticate(token);


        // find the user

        User user = authenticationService.getUser(token);

        // save the item in wishlist

        WishList wishList = new WishList(user, product);

        wishListService.createWishlist(wishList);

        ApiResponse apiResponse = new ApiResponse(true, "Added to wishlist");
        return  new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }


    // get all wishlist item for a user

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) {

        // authenticate the token
        authenticationService.authenticate(token);


        // find the user

        User user = authenticationService.getUser(token);

        List<ProductDto> productDtos = wishListService.getWishListForUser(user);

        return new ResponseEntity<>(productDtos, HttpStatus.OK);

    }
}
