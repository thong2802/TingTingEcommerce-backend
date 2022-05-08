package com.TingTing.ecommerce.controller;

import com.TingTing.ecommerce.common.ApiResponse;
import com.TingTing.ecommerce.dto.product.ProductDTO;
import com.TingTing.ecommerce.model.Product;
import com.TingTing.ecommerce.model.User;
import com.TingTing.ecommerce.model.Wishlist;
import com.TingTing.ecommerce.repository.WishlistRepository;
import com.TingTing.ecommerce.service.AuthenticationService;
import com.TingTing.ecommerce.service.ProductService;
import com.TingTing.ecommerce.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private AuthenticationService authenticationService;

    // save product as wishList
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product, @RequestParam("token") String token) {
       // authenticate token
        authenticationService.authenticate(token);
       // find the user
        User user = authenticationService.getUser(token);
       // save item in wishlist
        Wishlist wishlist = new Wishlist(user, product);
        wishlistService.createWishList(wishlist);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Add to wishlist"), HttpStatus.CREATED);
    }

    // get all wishlist product item for a user.
    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDTO>> getWishList(@PathVariable("token") String token) {
        authenticationService.authenticate(token);
        int user_id = authenticationService.getUser(token).getId();
        List<Wishlist> body = wishlistService.readWishList(user_id);
        List<ProductDTO> products = new ArrayList<>();
        for (Wishlist wishList : body) {
            products.add(ProductService.getDtoFromProduct(wishList.getProduct()));
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
