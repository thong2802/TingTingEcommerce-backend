package com.TingTing.ecommerce.dto.cart;

import java.util.List;

public class CartDto {
    private List<CartItemDto> cartItemDtos;
    private double totalCost;

    public CartDto(List<CartItemDto> cartItemDtos, double totalCost) {
        this.cartItemDtos = cartItemDtos;
        this.totalCost = totalCost;
    }

    public List<CartItemDto> getCartItemDtos() {
        return cartItemDtos;
    }

    public void setCartItemDtos(List<CartItemDto> cartItemDtos) {
        this.cartItemDtos = cartItemDtos;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
