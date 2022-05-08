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
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationService authenticationService;


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
    @GetMapping("/list")
    public ResponseEntity<CartDto> getAllListCart(@RequestParam("token") String token) {
        // authenticate token
        authenticationService.authenticate(token);
        // find the user
        User user = authenticationService.getUser(token);
        // get cart item
        CartDto cartDto = cartService.getAllListCart(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
    // delete cart item for a user
    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Integer cartItemId,@RequestParam("token") String token) {
        // authenticate token
        authenticationService.authenticate(token);
        // find the user
        User user = authenticationService.getUser(token);
        // delete
        cartService.deleteCartItem(cartItemId, user);
        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }
}
