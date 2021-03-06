package com.rmmcosta.MyCrud.services.mapServices;

import com.rmmcosta.MyCrud.bootstrap.BootstrapCustomers;
import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.domain.DomainObject;
import com.rmmcosta.MyCrud.services.CustomerService;
import com.rmmcosta.MyCrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@Profile("map")
public class CustomerMapServiceImpl extends AbstractMapService implements CustomerService {

    private UserService userService;

    public CustomerMapServiceImpl() {
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
    public List<Customer> listAllObjects() {
        return (List<Customer>) super.listAllObjects();
    }

    @Override
    public Customer getObjectById(int id) throws DomainObjectNotFound {
        return (Customer) super.getObjectById(id);
    }

    @Override
    public Customer createOrUpdateObject(Customer customer) throws DomainObjectNotFound {
        if (customer.getUserId() != 0 && (customer.getUser() == null || customer.getUserId() != customer.getUser().getId())) {
            try {
                customer.setUser(userService.getObjectById(customer.getUserId()));
            } catch (DomainObjectNotFound domainObjectNotFound) {
                domainObjectNotFound.printStackTrace();
            }
        }
        return (Customer) super.createOrUpdateObject(customer);
    }

    @Override
    public void deleteObject(int id) throws DomainObjectNotFound {
        super.deleteObject(id);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
