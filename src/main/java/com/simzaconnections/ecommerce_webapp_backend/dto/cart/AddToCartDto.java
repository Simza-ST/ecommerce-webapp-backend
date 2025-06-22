package com.simzaconnections.ecommerce_webapp_backend.dto.cart;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class AddToCartDto {

    private Integer id;
    private @NotNull Integer productId;
    private @NotNull Integer quantity;
}
