package com.rmmcosta.MyCrud.domain;

import javax.persistence.*;

@Entity
public class Customer implements DomainObject {
    private String firstName, lastName, phoneNumber, email;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "bill_address")),
            @AttributeOverride(name = "city", column = @Column(name = "bill_city")),
            @AttributeOverride(name = "state", column = @Column(name = "bill_state")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "bill_zipCode")),
            @AttributeOverride(name = "country", column = @Column(name = "bill_country"))
    })
    private Address billingAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "ship_address")),
            @AttributeOverride(name = "city", column = @Column(name = "ship_city")),
            @AttributeOverride(name = "state", column = @Column(name = "ship_state")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "ship_zipCode")),
            @AttributeOverride(name = "country", column = @Column(name = "ship_country"))
    })
    private Address shippingAddress;

    @Version
    private Integer version;

    @OneToOne(cascade = CascadeType.DETACH)
    private User user;

    @Transient
    private int userId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public boolean equals(Object obj) {
        Customer otherCustomer = (Customer) obj;
        boolean equal = true;
        if (this.getId() != otherCustomer.getId()) {
            equal = false;
        }
        if (this.getEmail() != otherCustomer.getEmail()) {
            equal = false;
        }
        if (this.getFirstName() != otherCustomer.getFirstName()) {
            equal = false;
        }
        if (this.getLastName() != otherCustomer.getLastName()) {
            equal = false;
        }
        if (this.getPhoneNumber() != otherCustomer.getPhoneNumber()) {
            equal = false;
        }
        return equal;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
