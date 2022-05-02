package com.TingTing.ecommerce.controller;
import com.TingTing.ecommerce.common.ApiResponse;
import com.TingTing.ecommerce.model.Category;
import com.TingTing.ecommerce.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "a new category created"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Category>> listCategory() {
        List<Category> body = categoryService.listCategory();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId,  @RequestBody Category category) {
        // System.out.println("CategoryId: " + categoryId);
        // check to see if the category exits
        if (categoryService.findById(categoryId)) {
            // if the category exits then update it.
            categoryService.editCategory(categoryId, category);
            return new ResponseEntity<>(new ApiResponse(true, "the product updated"), HttpStatus.OK);
        }
        // If the category doesn't exits then return a response of unsuccesful!
        return new ResponseEntity<>(new ApiResponse(false, "category not exits"), HttpStatus.NOT_FOUND);
    }
}
