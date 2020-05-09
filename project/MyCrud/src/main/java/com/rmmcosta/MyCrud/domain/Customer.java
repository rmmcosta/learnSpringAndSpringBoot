package com.rmmcosta.MyCrud.domain;

public class Customer implements DomainObject {
    private String firstName, lastName, phoneNumber, email, address, city, state, zipCode, country;
    private int id;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        Customer otherCustomer = (Customer) obj;
        boolean equal = true;
        if (this.getId() != otherCustomer.getId()) {
            equal = false;
        }
        if (this.getAddress() != otherCustomer.getAddress()) {
            equal = false;
        }
        if (this.getCity() != otherCustomer.getCity()) {
            equal = false;
        }
        if (this.getCountry() != otherCustomer.getCountry()) {
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
        if (this.getState() != otherCustomer.getState()) {
            equal = false;
        }
        if (this.getZipCode() != otherCustomer.getZipCode()) {
            equal = false;
        }
        if (this.getPhoneNumber() != otherCustomer.getPhoneNumber()) {
            equal = false;
        }
        return equal;
    }
}
