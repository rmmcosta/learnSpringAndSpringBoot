package com.rmmcosta.MyCrud.controllers;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Product;
import com.rmmcosta.MyCrud.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.listAllObjects());
        return "/Product/products";
    }

    @RequestMapping("/product/{id}")
    public String showProduct(Model model, @PathVariable int id) throws DomainObjectNotFound {
        model.addAttribute("product", productService.getObjectById(id));
        return "/Product/product";
    }

    @RequestMapping("/product/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "/Product/newProduct";
    }

    @RequestMapping("/product/edit/{id}")
    public String editProduct(Model model, @PathVariable int id) throws DomainObjectNotFound {
        model.addAttribute("product", productService.getObjectById(id));
        return "/Product/newProduct";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String createOrUpdateProduct(Product product) throws DomainObjectNotFound {
        int id = productService.createOrUpdateObject(product).getId();
        return "redirect:/product/" + id;
    }

    @RequestMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable int id) throws DomainObjectNotFound {
        productService.deleteObject(id);
        return "redirect:/products";
    }
}
