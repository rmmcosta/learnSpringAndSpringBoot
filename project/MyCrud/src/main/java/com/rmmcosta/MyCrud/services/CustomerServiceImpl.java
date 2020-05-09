package com.rmmcosta.MyCrud.services;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.domain.DomainObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CustomerServiceImpl extends AbstractService implements CustomerService {
    public CustomerServiceImpl() {
        super();
    }

    @Override
    protected void bootstrapObjects() {
        domainObjectMap = new HashMap<>();
        Customer customer = new Customer();
        customer.setAddress("Paço da Rainha, n.º22, 2º");
        customer.setCity("Lisbon");
        customer.setCountry("Portugal");
        customer.setEmail("rmmcosta@mail.com");
        customer.setFirstName("Ricardo");
        customer.setLastName("Costa");
        customer.setPhoneNumber("938012420");
        customer.setId(1);
        customer.setState("Lisbon");
        customer.setZipCode("1150-246");
        domainObjectMap.put(1, customer);

        customer = new Customer();
        customer.setAddress("Travessa de Matos, n.º115");
        customer.setCity("Guimarães");
        customer.setCountry("Portugal");
        customer.setEmail("costa@mail.com");
        customer.setFirstName("Rui");
        customer.setLastName("Costa");
        customer.setPhoneNumber("910274474");
        customer.setId(2);
        customer.setState("Braga");
        customer.setZipCode("4765-571");
        domainObjectMap.put(2, customer);
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
