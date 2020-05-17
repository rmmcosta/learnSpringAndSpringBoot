package com.rmmcosta.MyCrud.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Cart implements DomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date createdOn = new Date();

    @Version
    private int version;

    @Transient
    private int customerId;

    @Transient
    private int productId;

    @Transient
    private String customerEmail;

    @ManyToOne(cascade = CascadeType.DETACH, optional = false)
    private Customer customer;

    @OneToMany(cascade = CascadeType.DETACH)
    private List<Product> productList = new ArrayList<>();

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCustomerEmail() {
        return this.customer.getEmail();
    }
}
