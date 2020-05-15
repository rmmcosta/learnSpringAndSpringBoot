package com.rmmcosta.MyCrud.services;

import com.rmmcosta.MyCrud.bootstrap.BootstrapCustomers;
import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.domain.DomainObject;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractService implements CustomerService {
    public CustomerServiceImpl() {
        super();
    }

    @Override
    protected void bootstrapObjects() {
        domainObjectMap = new HashMap<>();
        for (Customer customer : BootstrapCustomers.getBootstrapCustomers()) {
            domainObjectMap.put(customer.getId(), customer);
        }
    }

    @Override
    public List<DomainObject> listAllObjects() {
        return super.listAllObjects();
    }

    @Override
    public Customer getObjectById(int id) throws DomainObjectNotFound {
        return (Customer) super.getObjectById(id);
    }

    @Override
    public Customer createOrUpdateObject(Customer customer) throws DomainObjectNotFound {
        return (Customer) super.createOrUpdateObject(customer);
    }

    @Override
    public void deleteObject(int id) throws DomainObjectNotFound {
        super.deleteObject(id);
    }
}
