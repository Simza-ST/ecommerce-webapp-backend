package com.simzaconnections.ecommerce_webapp_backend.controller;

import com.simzaconnections.ecommerce_webapp_backend.common.ApiResponse;
import com.simzaconnections.ecommerce_webapp_backend.dto.ProductDto;
import com.simzaconnections.ecommerce_webapp_backend.exceptions.ProductNotExistsException;
import com.simzaconnections.ecommerce_webapp_backend.model.Category;
import com.simzaconnections.ecommerce_webapp_backend.model.Product;
import com.simzaconnections.ecommerce_webapp_backend.repository.CategoryRepository;
import com.simzaconnections.ecommerce_webapp_backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "https://thembis-bazaar.netlify.app")
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;


    private final CategoryRepository categoryRepo;

    public ProductController(ProductService productService, CategoryRepository categoryRepo) {
        this.productService = productService;
        this.categoryRepo = categoryRepo;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exists"),
                    HttpStatus.BAD_REQUEST);
        }
        productService.createProduct(productDto, optionalCategory.get());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been added"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // create an api to edit the product

    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId,
                                                     @RequestBody ProductDto productDto) throws Exception {
        Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exists"),
                    HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDto, productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been updated"), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Integer productId) {
        try {
            Product product = productService.findById(productId);
            ProductDto productDto = productService.getProductDto(product);
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } catch (ProductNotExistsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
