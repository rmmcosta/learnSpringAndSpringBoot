package com.rmmcosta.MyCrud.customExceptions;

public class DomainObjectNotFound extends Exception {
    public DomainObjectNotFound() {
        super("Object not found!");
    }
}
