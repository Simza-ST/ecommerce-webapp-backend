package com.simzaconnections.ecommerce_webapp_backend.controller;

import com.simzaconnections.ecommerce_webapp_backend.common.ApiResponse;
import com.simzaconnections.ecommerce_webapp_backend.dto.cart.AddToCartDto;
import com.simzaconnections.ecommerce_webapp_backend.dto.cart.CartDto;
import com.simzaconnections.ecommerce_webapp_backend.dto.cart.CartItemDto;
import com.simzaconnections.ecommerce_webapp_backend.model.User;
import com.simzaconnections.ecommerce_webapp_backend.service.AuthenticationService;
import com.simzaconnections.ecommerce_webapp_backend.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "https://thembis-bazaar.netlify.app")
public class CartController {


    private final CartService cartService;
    private final AuthenticationService authenticationService;

    public CartController(CartService cartService, AuthenticationService authenticationService) {
        this.cartService = cartService;
        this.authenticationService = authenticationService;
    }

    // post cart api
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token) {
        // authenticate the token
        authenticationService.authenticate(token);

        // find the user

        User user = authenticationService.getUser(token);

        cartService.addToCart(addToCartDto, user);

        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }

    // get all cart items for a user
    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) {
        // authenticate the token
        authenticationService.authenticate(token);

        // find the user
        User user = authenticationService.getUser(token);

        // get cart items

        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    // delete a cart item for a user

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Integer itemId,
                                                      @RequestParam("token") String token) {

        // authenticate the token
        authenticationService.authenticate(token);

        // find the user
        User user = authenticationService.getUser(token);

        cartService.deleteCartItem(itemId, user);

        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);

    }

    @PatchMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItemQuantity(
            @PathVariable("cartItemId") Integer itemId,
            @RequestParam("token") String token,
            @RequestBody CartItemDto cartItemDto) {

        // authenticate the token
        authenticationService.authenticate(token);

        // find the user
        User user = authenticationService.getUser(token);

        // update the cart item
        cartService.updateCartItemQuantity(itemId, cartItemDto.getQuantity(), user);

        return new ResponseEntity<>(new ApiResponse(true, "Cart item quantity updated"), HttpStatus.OK);
    }

}
