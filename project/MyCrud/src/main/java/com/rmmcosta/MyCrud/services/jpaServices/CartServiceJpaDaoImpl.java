package com.rmmcosta.MyCrud.services.jpaServices;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Cart;
import com.rmmcosta.MyCrud.domain.Product;
import com.rmmcosta.MyCrud.services.CartService;
import com.rmmcosta.MyCrud.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Profile("jpadao")
public class CartServiceJpaDaoImpl extends AbstractJpaService implements CartService {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private CustomerService customerService;

    @Override
    public List<Cart> listAllObjects() {
        return entityManager.createQuery("from Cart", Cart.class).getResultList();
    }

    @Override
    public Cart getObjectById(int id) throws DomainObjectNotFound {
        return entityManager.find(Cart.class, id);
    }

    @Override
    public Cart createOrUpdateObject(Cart object) throws DomainObjectNotFound {
        if (object.getCustomerId() != 0 && (object.getCustomer() == null || object.getCustomerId() != object.getCustomer().getId())) {
            object.setCustomer(customerService.getObjectById(object.getCustomerId()));
        }
        entityManager.getTransaction().begin();
        Cart newCart = entityManager.merge(object);
        entityManager.getTransaction().commit();
        return newCart;
    }

    @Override
    public void deleteObject(int id) throws DomainObjectNotFound {
        Cart cart = entityManager.find(Cart.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(cart);
        entityManager.getTransaction().commit();
    }

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    int getCount() {
        return entityManager.createNativeQuery("Select count(1) from Cart").getFirstResult();
    }

    @Override
    public List<Product> getCartProducts(int id) {
        System.out.println("my print: id=" + id);
        Query q = entityManager.createNativeQuery("SELECT p.ID id, p.DESCRIPTION description, " +
                        "p.NAME name, p.PRICE price " +
                "FROM CART_PRODUCT_LIST cp " +
                "inner join Product p on p.id = cp.product_list_id where cart_id = :id");
        q.setParameter("id", id);
        List<Object[]> objects = q.getResultList();
        List<Product> productList = new ArrayList<>();
        Product product;
        for (Object[] lines : objects) {
            product = new Product();
            product.setId((Integer) lines[0]);
            product.setDescription((String) lines[1]);
            product.setName((String) lines[2]);
            product.setPrice((BigDecimal) lines[3]);
            productList.add(product);
        }
        return productList;
    }

    @Override
    public void addProduct(Cart cart) {
        entityManager.getTransaction().begin();
        System.out.println("my print: id = " + cart.getId());
        System.out.println("my print: product id = " + cart.getProductId());
        entityManager.createNativeQuery("INSERT INTO CART_PRODUCT_LIST (cart_id, product_list_id) VALUES (?,?)")
                .setParameter(1, cart.getId())
                .setParameter(2, cart.getProductId())
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
}
