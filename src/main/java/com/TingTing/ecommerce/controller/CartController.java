package com.TingTing.ecommerce.controller;

import com.TingTing.ecommerce.common.ApiResponse;
import com.TingTing.ecommerce.dto.cart.AddToCartDto;
import com.TingTing.ecommerce.dto.cart.CartDto;
import com.TingTing.ecommerce.model.AuthenticationToken;
import com.TingTing.ecommerce.model.Product;
import com.TingTing.ecommerce.model.User;
import com.TingTing.ecommerce.service.AuthenticationService;
import com.TingTing.ecommerce.service.CartService;
import com.TingTing.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    AuthenticationService authenticationService;


    // POST CART API
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token) {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        cartService.addToCart(addToCartDto, user);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }
    // get all cart item for a user

    // delete cart item for a user
}
