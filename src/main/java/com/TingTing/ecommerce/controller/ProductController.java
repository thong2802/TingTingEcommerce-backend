package com.TingTing.ecommerce.controller;

import com.TingTing.ecommerce.common.ApiResponse;
import com.TingTing.ecommerce.dto.ProductDTO;
import com.TingTing.ecommerce.model.Category;
import com.TingTing.ecommerce.repository.CategoryRepo;
import com.TingTing.ecommerce.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
   private ProductService productService;

    @Autowired
    private CategoryRepo categoryRepo;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO productDTO) {
       Optional<Category> optionalCategory =  categoryRepo.findById(productDTO.getCategoryId());
       // if check categoryId is false
       if (!optionalCategory.isPresent()) {
           // if category_Id doesn't  exits, then return a response of unsuccessful
           return new ResponseEntity<>(new ApiResponse(false, "category doesn't exits"), HttpStatus.BAD_REQUEST);
       }
        // if check categoryId is false
        // then create a new product
        productService.createProduct(productDTO, optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(true, "a new product created"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> listProducts() {
        List<ProductDTO> body = productService.getAllProducts();
        return new ResponseEntity<List<ProductDTO>>(body, HttpStatus.OK);
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId ,@RequestBody @Valid ProductDTO productDTO) throws Exception {
        Optional<Category> optionalCategory =  categoryRepo.findById(productDTO.getCategoryId());
        // if check categoryId is false
        if (!optionalCategory.isPresent()) {
            // if category_Id doesn't  exits, then return a response of unsuccessful
            return new ResponseEntity<>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        // if check categoryId is false
        // then create a new product
        productService.updateProduct(productId,productDTO);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.CREATED);
    }
}
