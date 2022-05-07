package com.TingTing.ecommerce.service;

import com.TingTing.ecommerce.dto.product.ProductDTO;
import com.TingTing.ecommerce.model.User;
import com.TingTing.ecommerce.model.Wishlist;
import com.TingTing.ecommerce.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    ProductService productService;

    public void createWishList(Wishlist wishlist) {
        wishlistRepository.save(wishlist);
    }
    public List<Wishlist> readWishList(Integer userId) {
        return wishlistRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
    }
}
