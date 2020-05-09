package com.rmmcosta.MyCrud.services;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.domain.DomainObject;

import java.util.List;

public interface CrudService<T> {
    List<?> listAllObjects();
    T getObjectById(int id) throws DomainObjectNotFound;
    T createOrUpdateObject(T object) throws DomainObjectNotFound;
    void deleteObject(int id) throws DomainObjectNotFound;
}
