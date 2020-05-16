package com.rmmcosta.MyCrud.services.jpaServices;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.services.CustomerService;
import com.rmmcosta.MyCrud.services.UserService;
import com.rmmcosta.MyCrud.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
@Profile("jpadao")
public class CustomerServiceJpaDaoImpl implements CustomerService {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EncryptionService encryptionService;
    private UserService userService;

    @Override
    public List<Customer> listAllObjects() {
        return entityManager.createQuery("from Customer", Customer.class).getResultList();
    }

    @Override
    public Customer getObjectById(int id) throws DomainObjectNotFound {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public Customer createOrUpdateObject(Customer object) {
        if (object.getUserId() != 0 && (object.getUser() == null || object.getUserId() != object.getUser().getId())) {
            System.out.println("my print: user id passed=" + object.getUserId());
            try {
                object.setUser(userService.getObjectById(object.getUserId()));
                System.out.println("my print: " + userService.getObjectById(object.getUserId()).getUsername());
            } catch (DomainObjectNotFound domainObjectNotFound) {
                domainObjectNotFound.printStackTrace();
            }
        }
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        Customer newCustomer = entityManager.merge(object);
        entityManager.getTransaction().commit();
        return newCustomer;
    }

    @Override
    public void deleteObject(int id) throws DomainObjectNotFound {
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(customer);
        entityManager.getTransaction().commit();
    }

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
