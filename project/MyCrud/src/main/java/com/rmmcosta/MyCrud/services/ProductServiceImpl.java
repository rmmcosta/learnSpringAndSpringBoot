package com.rmmcosta.MyCrud.services;

import com.rmmcosta.MyCrud.domain.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private Map<Integer, Product> products;

    public ProductServiceImpl() {
        bootstrapProducts();
    }

    private void bootstrapProducts() {
        products = new HashMap<Integer, Product>(4);
        Product product;

        product = new Product();
        product.setId(1);
        product.setName("Samsung S10");
        product.setDescription("Smartphone running Android 9.");
        product.setPrice(new BigDecimal("679.99"));
        product.setImageUrl("https://www.worten.pt/i/af6e5cad58f7001b1ebb0e536e45dc12c0d2a76c.jpg");
        product.setCreatedOn(new Date());
        products.put(1, product);

        product = new Product();
        product.setId(2);
        product.setName("iPhone XR");
        product.setDescription("Smartphone running iOS 12.");
        product.setPrice(new BigDecimal("739.99"));
        product.setImageUrl("https://www.worten.pt/i/66a71615da0f9ca5c10be0d34843e7ccfcb1cef9.jpg");
        product.setCreatedOn(new Date());
        products.put(2, product);

        product = new Product();
        product.setId(3);
        product.setName("Samsung S20");
        product.setDescription("Smartphone running Android 10.");
        product.setPrice(new BigDecimal("929.99"));
        product.setImageUrl("https://www.worten.pt/i/c753e9bac4f1a6d0cbe8a2be63134768f1c29e0a.jpg");
        product.setCreatedOn(new Date());
        products.put(3, product);

        product = new Product();
        product.setId(4);
        product.setName("iPhone 11");
        product.setDescription("Smartphone running iOS 13.");
        product.setPrice(new BigDecimal("829.99"));
        product.setImageUrl("https://www.worten.pt/i/ce3be0c610c24f2929c82cc6c04f2aa5ddb90189.jpg");
        product.setCreatedOn(new Date());
        products.put(4, product);
    }

    @Override
    public List<Product> listAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product getProduct(int id) {
        return products.get(id);
    }

    @Override
    public Product createOrUpdateProduct(Product product) {
        if (product.getId() == 0) {
            return createProduct(product.getName(), product.getDescription(), product.getImageUrl(), product.getPrice());
        } else {
            return updateProduct(product.getId(), product.getName(), product.getDescription(), product.getImageUrl(), product.getPrice());
        }
    }

    @Override
    public int getNextId() {
        return products.size() + 1;
    }

    @Override
    public void deleteProduct(int id) {
        if (products.containsKey(id)) {
            products.remove(id);
        } else {
            throw new RuntimeException("Product not found!");
        }
    }

    @Override
    public int getNumProducts() {
        return products.size();
    }

    private Product createProduct(String name, String description, String imageUrl, BigDecimal price) {
        Product product = new Product();
        int newId = getNextId();
        product.setId(newId);
        product.setImageUrl(imageUrl);
        product.setCreatedOn(new Date());
        product.setPrice(price);
        product.setName(name);
        product.setDescription(description);
        products.put(newId, product);
        return product;
    }

    private Product updateProduct(int id, String name, String description, String imageUrl, BigDecimal price) {
        if (products.containsKey(id)) {
            Product product = products.get(id);
            product.setImageUrl(imageUrl);
            product.setUpdatedOn(new Date());
            product.setPrice(price);
            product.setName(name);
            product.setDescription(description);
            return product;
        } else {
            throw new RuntimeException("Product not found!");
        }
    }
}
