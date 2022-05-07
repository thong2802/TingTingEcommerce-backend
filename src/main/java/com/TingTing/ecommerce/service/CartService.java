package com.TingTing.ecommerce.service;

import com.TingTing.ecommerce.dto.cart.AddToCartDto;
import com.TingTing.ecommerce.model.Cart;
import com.TingTing.ecommerce.model.Product;
import com.TingTing.ecommerce.model.User;
import com.TingTing.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductService productService;
    public void addToCart(AddToCartDto addToCartDto, User user) {
        // validate if the product id is valid
         Product product = productService.getProductById(addToCartDto.getProductId());

        // save the cart
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }
}
