package com.simzaconnections.ecommerce_webapp_backend.service;

import com.simzaconnections.ecommerce_webapp_backend.dto.cart.AddToCartDto;
import com.simzaconnections.ecommerce_webapp_backend.dto.cart.CartDto;
import com.simzaconnections.ecommerce_webapp_backend.dto.cart.CartItemDto;
import com.simzaconnections.ecommerce_webapp_backend.exceptions.CustomException;
import com.simzaconnections.ecommerce_webapp_backend.model.Cart;
import com.simzaconnections.ecommerce_webapp_backend.model.Product;
import com.simzaconnections.ecommerce_webapp_backend.model.User;
import com.simzaconnections.ecommerce_webapp_backend.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {


    private final ProductService productService;

    private final CartRepository cartRepository;

    public CartService(ProductService productService, CartRepository cartRepository) {
        this.productService = productService;
        this.cartRepository = cartRepository;
    }

    public void addToCart(AddToCartDto addToCartDto, User user) {

        // validate if the product id is valid
        Product product = productService.findById(addToCartDto.getProductId());

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreatedDate(new Date());


        // save the cart
        cartRepository.save(cart);

    }

    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        List<CartItemDto> cartItems = new ArrayList<>();
        double totalCost = 0;
        for (Cart cart: cartList) {
            CartItemDto cartItemDto = new CartItemDto(cart.getQuantity(), cart.getProduct());
            totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
            cartItems.add(cartItemDto);
        }

        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItems(cartItems);
        return  cartDto;
    }

    public void deleteCartItem(Integer cartItemId, User user) {
        // the item id belongs to user

        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);

        if (optionalCart.isEmpty()) {
            throw new CustomException("cart item id is invalid: " + cartItemId);
        }

        Cart cart = optionalCart.get();

        if (cart.getUser() != user) {
            throw  new CustomException("cart item does not belong to user: " +cartItemId);
        }

        cartRepository.delete(cart);


    }

    public void updateCartItemQuantity(Integer cartItemId, Integer newQuantity, User user) {
        // Check if the cart item exists and belongs to the user
        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);
        if (optionalCart.isEmpty()) {
            throw new CustomException("Cart item id is invalid: " + cartItemId);
        }

        Cart cart = optionalCart.get();
        if (!cart.getUser().equals(user)) {
            throw new CustomException("Cart item does not belong to user: " + cartItemId);
        }

        // Check if the new quantity is valid
        if (newQuantity <= 0) {
            throw new CustomException("Quantity must be greater than 0");
        }

        // Update the quantity of the cart item
        cart.setQuantity(newQuantity);
        cartRepository.save(cart);
    }
}
