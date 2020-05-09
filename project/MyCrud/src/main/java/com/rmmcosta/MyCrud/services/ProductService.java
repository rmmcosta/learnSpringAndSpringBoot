package com.rmmcosta.MyCrud.services;

import com.rmmcosta.MyCrud.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAllProducts();
    Product getProduct(int id);
}
