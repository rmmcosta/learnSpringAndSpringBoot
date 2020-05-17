package com.rmmcosta.MyCrud.services.jpaServices;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Cart;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.domain.Product;
import com.rmmcosta.MyCrud.services.CartService;
import com.rmmcosta.MyCrud.services.CustomerService;
import com.rmmcosta.MyCrud.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("jpadao")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class CartServiceJpaDaoImplTest {
    private CartService cartService;

    private CustomerService customerService;
    private ProductService productService;
    private AbstractJpaService jpaService;

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
        this.jpaService = (AbstractJpaService) cartService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    void listAllObjects() {
        assertEquals(jpaService.getCount(), cartService.listAllObjects().size());
    }

    @Test
    void getObjectById() throws DomainObjectNotFound {
        Cart cart = new Cart();
        Customer customer = (Customer) customerService.listAllObjects().get(0);
        cart.setCustomerId(customer.getId());
        Cart newCart = cartService.createOrUpdateObject(cart);
        cart = (Cart) cartService.listAllObjects().get(0);
        assertEquals(cart.getCreatedOn(), cartService.getObjectById(cart.getId()).getCreatedOn());
        cartService.deleteObject(newCart.getId());
    }

    @Test
    void createOrUpdateObject() throws DomainObjectNotFound {
        int initialSize = cartService.listAllObjects().size();
        Cart cart = new Cart();
        Date cartDate = new Date();
        Customer customer = (Customer) customerService.listAllObjects().get(0);
        cart.setCustomerId(customer.getId());
        Cart newCart = cartService.createOrUpdateObject(cart);
        assertEquals(cartDate, newCart.getCreatedOn());
        assertEquals(initialSize + 1, cartService.listAllObjects().size());
        assertEquals(cartDate, cartService.getObjectById(newCart.getId()).getCreatedOn());
        cartService.deleteObject(newCart.getId());
    }

    @Test
    void deleteObject() throws DomainObjectNotFound {
        Cart cart = new Cart();
        Customer customer = (Customer) customerService.listAllObjects().get(0);
        cart.setCustomerId(customer.getId());
        Cart newCart = cartService.createOrUpdateObject(cart);
        List<Cart> cartList = (List<Cart>) cartService.listAllObjects();
        int initialSize = cartList.size();
        cartService.deleteObject(newCart.getId());
        assertEquals(initialSize - 1, cartService.listAllObjects().size());
    }

    @Test
    void getCount() {
        int size = cartService.listAllObjects().size();
        AbstractJpaService jpaService = (AbstractJpaService) cartService;
        int count = jpaService.getCount();
        assertEquals(size, count);
    }

    @Test
    void getCartProducts() throws DomainObjectNotFound {
        Cart cart = new Cart();
        Customer customer = (Customer) customerService.listAllObjects().get(0);
        cart.setCustomerId(customer.getId());
        Cart newCart = cartService.createOrUpdateObject(cart);
        Cart productCart = new Cart();
        productCart.setId(newCart.getId());
        Product product = (Product) productService.listAllObjects().get(0);
        productCart.setProductId(product.getId());
        cartService.addProduct(productCart);
        List<Product> cartProducts = cartService.getCartProducts(newCart.getId());
        for (Product p : cartProducts) {
            System.out.println(p.getName());
        }
        assertEquals(1,cartProducts.size());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void removeProduct() throws DomainObjectNotFound {
        Cart cart = new Cart();
        Customer customer = (Customer) customerService.listAllObjects().get(0);
        cart.setCustomerId(customer.getId());
        Cart newCart = cartService.createOrUpdateObject(cart);
        Cart productCart = new Cart();
        productCart.setId(newCart.getId());
        Product product = (Product) productService.listAllObjects().get(0);
        productCart.setProductId(product.getId());
        cartService.addProduct(productCart);
        int deletedCount = cartService.removeProduct(productCart.getId(), productCart.getProductId());
        assertEquals(1, deletedCount);
    }

    @Test
    void testGetCount() throws DomainObjectNotFound {
        int initCount = jpaService.getCount();
        Cart cart = new Cart();
        Customer customer = (Customer) customerService.listAllObjects().get(0);
        cart.setCustomerId(customer.getId());
        Cart newCart = cartService.createOrUpdateObject(cart);
        assertEquals(initCount+1,jpaService.getCount());
        cartService.deleteObject(newCart.getId());
    }
}