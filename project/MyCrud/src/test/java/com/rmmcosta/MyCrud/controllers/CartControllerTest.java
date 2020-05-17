package com.rmmcosta.MyCrud.controllers;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Cart;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.services.CartService;
import com.rmmcosta.MyCrud.services.CustomerService;
import com.rmmcosta.MyCrud.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CartControllerTest {

    @Mock
    private CartService cartService;
    @Mock
    private ProductService productService;
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CartController cartController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    void listCarts() {
        try {
            List<Cart> cartList = new ArrayList<>();
            cartList.add(new Cart());
            when(cartService.listAllObjects()).thenReturn((List) cartList);
            mockMvc.perform(get("/carts")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/Cart/carts")).
                    andExpect(model().attribute("carts", hasSize(1)));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    private Cart generateCart(int id, int customerId) throws DomainObjectNotFound {
        Cart cart = new Cart();
        cart.setId(id);
        cart.setCustomerId(customerId);
        return cart;
    }

    @Test
    void showCart() throws DomainObjectNotFound {
        int id = 10;
        Cart cart = generateCart(10, 5);
        try {
            when(cartService.getObjectById(10)).thenReturn(cart);
        } catch (DomainObjectNotFound domainObjectNotFound) {
            assertFalse(true);
        }
        try {
            mockMvc.perform(get("/cart/10")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/Cart/cart")).
                    andExpect(model().attribute("cart", instanceOf(Cart.class))).
                    andExpect(model().attribute("cart", hasProperty("id", is(id))));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void editCart() throws DomainObjectNotFound {
        generateCart(1, 5);
        try {
            mockMvc.perform(get("/cart/edit/1")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/Cart/newCart"));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void newCart() {
        verifyNoInteractions(cartService);
        try {
            mockMvc.perform(get("/carts/new")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/Cart/newCart")).
                    andExpect(model().attribute("cart", instanceOf(Cart.class)));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void createOrUpdateCart() throws DomainObjectNotFound {
        int id = 100;
        Cart cart = generateCart(id, 5);
        try {
            when(cartService.createOrUpdateObject(Mockito.any(cart.getClass()))).thenReturn(cart);
        } catch (DomainObjectNotFound domainObjectNotFound) {
            System.out.println("when error: " + domainObjectNotFound.getMessage());
            assertFalse(true);
        }
        try {
            mockMvc.perform(post("/cart")
                    .param("id", String.valueOf(id))
                    .param("customerId", "5")).
                    andExpect(status().is3xxRedirection()).
                    andExpect(view().name("redirect:/cart/" + id)).
                    andExpect(model().attribute("cart", instanceOf(Cart.class)));
        } catch (Exception e) {
            System.out.println("perform error:" + e.getMessage());
            assertFalse(true);
        }

        ArgumentCaptor<Cart> boundCart = ArgumentCaptor.forClass(Cart.class);
        try {
            verify(cartService).createOrUpdateObject(boundCart.capture());
            assertEquals(id, boundCart.getValue().getId());
        } catch (DomainObjectNotFound domainObjectNotFound) {
            System.out.println("error bounding: " + domainObjectNotFound.getMessage());
            assertFalse(true);
        }
    }

    @Test
    void deleteCart() throws DomainObjectNotFound {
        int id = 1;
        generateCart(id, 5);
        try {
            mockMvc.perform(get("/cart/delete/" + id)).
                    andExpect(status().isFound()).
                    andExpect(view().name("redirect:/carts"));
        } catch (Exception e) {
            assertFalse(true);
        }
        try {
            verify(cartService, times(1)).deleteObject(id);
        } catch (DomainObjectNotFound domainObjectNotFound) {
            System.out.println("error verifying delete: " + domainObjectNotFound.getMessage());
            assertFalse(true);
        }
    }
}