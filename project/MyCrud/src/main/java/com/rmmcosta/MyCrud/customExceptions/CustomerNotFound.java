package com.rmmcosta.MyCrud.customExceptions;

public class CustomerNotFound extends Exception {
    public CustomerNotFound() {
        super("Customer not found!");
    }
}
