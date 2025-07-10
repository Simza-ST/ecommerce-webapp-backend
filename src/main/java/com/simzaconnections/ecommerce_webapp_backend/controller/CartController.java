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
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {

    private final CartService cartService;
    private final AuthenticationService authenticationService;

    public CartController(CartService cartService, AuthenticationService authenticationService) {
        this.cartService = cartService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(
             @RequestBody AddToCartDto addToCartDto,
            @RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return new ResponseEntity<>(
                        new ApiResponse(false, "Authorization header missing or invalid"),
                        HttpStatus.UNAUTHORIZED
                );
            }
            String token = authHeader.substring(7); // Remove "Bearer " prefix
            authenticationService.authenticate(token);
            User user = authenticationService.getUser(token);
            cartService.addToCart(addToCartDto, user);
            return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse(false, e.getMessage() != null ? e.getMessage() : "Failed to add item to cart"),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return null;
            }
            String token = authHeader.substring(7);
            authenticationService.authenticate(token);
            User user = authenticationService.getUser(token);
            CartDto cartDto = cartService.listCartItems(user);
            return new ResponseEntity<>(cartDto, HttpStatus.OK);
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(
            @PathVariable("cartItemId") Integer itemId,
            @RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return new ResponseEntity<>(
                        new ApiResponse(false, "Authorization header missing or invalid"),
                        HttpStatus.UNAUTHORIZED
                );
            }
            String token = authHeader.substring(7);
            authenticationService.authenticate(token);
            User user = authenticationService.getUser(token);
            cartService.deleteCartItem(itemId, user);
            return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse(false, e.getMessage() != null ? e.getMessage() : "Failed to remove cart item"),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PatchMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItemQuantity(
            @PathVariable("cartItemId") Integer itemId,
            @RequestHeader("Authorization") String authHeader,
            @RequestBody CartItemDto cartItemDto) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return new ResponseEntity<>(
                        new ApiResponse(false, "Authorization header missing or invalid"),
                        HttpStatus.UNAUTHORIZED
                );
            }
            String token = authHeader.substring(7);
            authenticationService.authenticate(token);
            User user = authenticationService.getUser(token);
            cartService.updateCartItemQuantity(itemId, cartItemDto.getQuantity(), user);
            return new ResponseEntity<>(new ApiResponse(true, "Cart item quantity updated"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse(false, e.getMessage() != null ? e.getMessage() : "Failed to update cart item"),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}