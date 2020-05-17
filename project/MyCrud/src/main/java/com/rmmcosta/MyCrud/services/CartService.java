package com.rmmcosta.MyCrud.services;

import com.rmmcosta.MyCrud.domain.Cart;
import com.rmmcosta.MyCrud.domain.Product;

import java.util.List;

public interface CartService extends CrudService<Cart> {
    List<Product> getCartProducts(int id);

    void addProduct(Cart cart);

    int removeProduct(int cartId, int productId);
}
