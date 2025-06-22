package com.simzaconnections.ecommerce_webapp_backend.controller;

import com.simzaconnections.ecommerce_webapp_backend.common.ApiResponse;
import com.simzaconnections.ecommerce_webapp_backend.model.Category;
import com.simzaconnections.ecommerce_webapp_backend.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "https://thembis-bazaar.netlify.app")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "a new category created"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Category> listCategory() {
        return categoryService.listCategory();
    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category ) {
        System.out.println("category id " + categoryId);
        if (!categoryService.findById(categoryId)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exists"), HttpStatus.NOT_FOUND);
        }
        categoryService.editCategory(categoryId, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "category has been updated"), HttpStatus.OK);
    }
}
