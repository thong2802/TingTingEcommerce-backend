package com.TingTing.ecommerce.service;

import com.TingTing.ecommerce.dto.cart.AddToCartDto;
import com.TingTing.ecommerce.dto.cart.CartDto;
import com.TingTing.ecommerce.dto.cart.CartItemDto;
import com.TingTing.ecommerce.exceptions.CartItemNotExistException;
import com.TingTing.ecommerce.exceptions.CustomException;
import com.TingTing.ecommerce.model.Cart;
import com.TingTing.ecommerce.model.Product;
import com.TingTing.ecommerce.model.User;
import com.TingTing.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public CartDto getAllListCart(User user) {
        List<Cart> cartList  = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItem = new ArrayList<>();
        for (Cart cart : cartList) {
            CartItemDto cartItemDto = new CartItemDto(cart);
            cartItem.add(cartItemDto);
        }
        double totalsCost = 0;
        for (CartItemDto cartItemDto : cartItem) {
            totalsCost += (cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity());
        }
        return new CartDto(cartItem, totalsCost);
    }

    public void deleteCartItem(Integer cartItemId, User user) {
        // check itemId have valid
        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);
        if (optionalCart.isEmpty()){
            throw new CartItemNotExistException("Cart item not valid: " + cartItemId);
        }
        // the item id belongs to user
        Cart cart = optionalCart.get();
        if (cart.getUser() != user)  {
            throw new CustomException("Cart item not belong to the user: " + cartItemId);
        }
        cartRepository.deleteById(cartItemId);
    }
}
