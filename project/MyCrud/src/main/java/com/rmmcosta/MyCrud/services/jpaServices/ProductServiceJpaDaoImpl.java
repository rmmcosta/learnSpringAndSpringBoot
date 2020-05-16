package com.rmmcosta.MyCrud.services.jpaServices;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Product;
import com.rmmcosta.MyCrud.services.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@Profile("jpadao")
public class ProductServiceJpaDaoImpl implements ProductService {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Override
    public List<Product> listAllObjects() {
        System.out.println("my print: list all objects");
        List<Product> products = entityManager.createQuery("from Product", Product.class).getResultList();
        return products;
    }

    @Override
    public Product getObjectById(int id) throws DomainObjectNotFound {
        System.out.println("my print: get object with id = " + id);
        //because we want this to be thread safe we must create an entity manager everytime
        return entityManager.find(Product.class, id);
    }

    @Override
    public Product createOrUpdateObject(Product object) throws DomainObjectNotFound {
        System.out.println("my print: create or update object");
        entityManager.getTransaction().begin();
        Product newProduct = entityManager.merge(object);
        entityManager.getTransaction().commit();
        return newProduct;
    }

    @Override
    public void deleteObject(int id) throws DomainObjectNotFound {
        System.out.println("my print: delete object");
        Product product = entityManager.find(Product.class, id);
        System.out.println("my print: got product " + product.getName());
        entityManager.getTransaction().begin();
        entityManager.remove(product);
        entityManager.getTransaction().commit();
    }

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        entityManager = entityManagerFactory.createEntityManager();
        System.out.println("my print: factory created");
    }
}
