package com.TingTing.ecommerce.exceptions;

public class CartItemNotExistException extends IllegalArgumentException{
    public CartItemNotExistException(String msg) {
        super(msg);
    }
}
