package com.rmmcosta.MyCrud.services;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
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

    @Override
    public List<Customer> listAllObjects() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("from Customer", Customer.class).getResultList();
    }

    @Override
    public Customer getObjectById(int id) throws DomainObjectNotFound {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Customer.class, id);
    }

    @Override
    public Customer createOrUpdateObject(Customer object) throws DomainObjectNotFound {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Customer newCustomer = entityManager.merge(object);
        entityManager.getTransaction().commit();
        return newCustomer;
    }

    @Override
    public void deleteObject(int id) throws DomainObjectNotFound {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Customer.class, id));
        entityManager.getTransaction().commit();
    }

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
}
