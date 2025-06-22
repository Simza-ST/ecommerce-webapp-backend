package com.simzaconnections.ecommerce_webapp_backend.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class ProductDto {
    // for create it can be optional
    // for update we need the id
    private Integer id;

    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull Integer categoryId;

}
