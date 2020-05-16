package com.rmmcosta.MyCrud.services.jpaServices;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.services.CustomerService;
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

    @Override
    public List<Customer> listAllObjects() {
        return entityManager.createQuery("from Customer", Customer.class).getResultList();
    }

    @Override
    public Customer getObjectById(int id) throws DomainObjectNotFound {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public Customer createOrUpdateObject(Customer object) throws DomainObjectNotFound {
        if (object.getUser() != null) {
            if (!object.getUser().getPassword().isEmpty()) {
                object.getUser().setEncryptedPassword(encryptionService.encryptPassword(object.getUser().getPassword()));
            }
        }
        entityManager.getTransaction().begin();
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
}
