package com.rmmcosta.MyCrud.controllers;

import com.rmmcosta.MyCrud.customExceptions.CustomerNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {
    CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    //route to list
    @RequestMapping("/customers")
    public String listAllCustomers(Model model) {
        model.addAttribute("customers", customerService.listAllCustomers());
        return "/Customer/customers";
    }

    //route to detail
    @RequestMapping("/customer/{id}")
    public String getCustomerById(Model model, @PathVariable int id) throws CustomerNotFound {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "/Customer/customer";
    }

    //route to delete
    @RequestMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable int id) throws CustomerNotFound {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }

    //route to edit
    @RequestMapping("/customer/edit/{id}")
    public String editCustomer(Model model, @PathVariable int id) throws CustomerNotFound {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "/Customer/newCustomer";
    }

    //route to new
    @RequestMapping("/customer/new")
    public String newCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "/Customer/newCustomer";
    }

    //route to create or update (post)
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String createOrUpdateCustomer(Customer customer) throws CustomerNotFound {
        int id = customerService.createOrUpdateCustomer(customer).getId();
        return "redirect:/customer/" + id;
    }
}
