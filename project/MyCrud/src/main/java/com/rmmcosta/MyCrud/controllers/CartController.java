package com.rmmcosta.MyCrud.controllers;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Cart;
import com.rmmcosta.MyCrud.domain.Product;
import com.rmmcosta.MyCrud.services.CartService;
import com.rmmcosta.MyCrud.services.CustomerService;
import com.rmmcosta.MyCrud.services.ProductService;
import com.rmmcosta.MyCrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {
    private CartService cartService;
    private ProductService productService;
    private CustomerService customerService;

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/carts")
    public String listCarts(Model model) {
        model.addAttribute("carts", cartService.listAllObjects());
        return "/Cart/carts";
    }

    @RequestMapping("/cart/{id}")
    public String showCart(Model model, @PathVariable int id) throws DomainObjectNotFound {
        model.addAttribute("cart", cartService.getObjectById(id));
        model.addAttribute("customer", cartService.getObjectById(id).getCustomer());
        model.addAttribute("products", productService.listAllObjects());
        model.addAttribute("cartProducts", cartService.getCartProducts(id));
        return "/Cart/cart";
    }

    @RequestMapping("/cart/edit/{id}")
    public String editCart(Model model, @PathVariable int id) throws DomainObjectNotFound {
        model.addAttribute("cart", cartService.getObjectById(id));
        model.addAttribute("products", productService.listAllObjects());
        model.addAttribute("customers", customerService.listAllObjects());
        return "/Cart/newCart";
    }

    @RequestMapping("/carts/new")
    public String newCart(Model model) {
        model.addAttribute("cart", new Cart());
        model.addAttribute("customers", customerService.listAllObjects());
        return "/Cart/newCart";
    }

    @PostMapping("/cart")
    public String createOrUpdateCart(Cart cart) throws DomainObjectNotFound {
        System.out.println("my print: create or update cart");
        int id = cartService.createOrUpdateObject(cart).getId();
        return "redirect:/cart/" + id;
    }

    @PostMapping("/cartProduct")
    public String newCartProduct(Cart cart) {
        cartService.addProduct(cart);
        return "redirect:/cart/" + cart.getId();
    }

    @RequestMapping("/cartProduct/delete/{cartId}/{productId}")
    public String deleteCartProduct(@PathVariable(name = "cartId") int cartId,
                                    @PathVariable(name = "productId") int productId) {
        cartService.removeProduct(cartId, productId);
        return "redirect:/cart/" + cartId;
    }

    @RequestMapping("/cart/delete/{id}")
    public String deleteCart(@PathVariable int id) throws DomainObjectNotFound {
        cartService.deleteObject(id);
        return "redirect:/carts";
    }
}
