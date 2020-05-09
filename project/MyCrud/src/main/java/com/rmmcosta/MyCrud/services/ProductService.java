package com.rmmcosta.MyCrud.services;

import com.rmmcosta.MyCrud.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAllProducts();

    Product getProduct(int id);

    Product createOrUpdateProduct(Product product);

    int getNextId();

    void deleteProduct(int id);

    int getNumProducts();
}
