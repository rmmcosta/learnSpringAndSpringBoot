package com.rmmcosta.MyCrud.controllers;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.domain.User;
import com.rmmcosta.MyCrud.services.CustomerService;
import com.rmmcosta.MyCrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CustomerController {
    private CustomerService customerService;
    private UserService userService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    //route to list
    @RequestMapping("/customers")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.listAllObjects());
        return "/Customer/customers";
    }

    //route to detail
    @RequestMapping("/customer/{id}")
    public String showCustomer(Model model, @PathVariable int id) throws DomainObjectNotFound {
        model.addAttribute("customer", customerService.getObjectById(id));
        model.addAttribute("users", userService.listAllObjects());
        return "/Customer/customer";
    }

    //route to delete
    @RequestMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable int id) throws DomainObjectNotFound {
        customerService.deleteObject(id);
        return "redirect:/customers";
    }

    //route to edit
    @RequestMapping("/customer/edit/{id}")
    public String editCustomer(Model model, @PathVariable int id) throws DomainObjectNotFound {
        Customer customer = customerService.getObjectById(id);
        model.addAttribute("customer", customer);
        List<User> userList = (List<User>) userService.listAllObjects();
        User emptyUser = new User();
        emptyUser.setUsername(" - ");
        emptyUser.setId(0);
        userList.add(emptyUser);
        model.addAttribute("users", userList);
        if (customer.getUser() != null) {
            model.addAttribute("selectedUserId", customer.getUser().getId());
        }
        return "/Customer/newCustomer";
    }

    //route to new
    @RequestMapping("/customer/new")
    public String newCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        List<User> userList = (List<User>) userService.listAllObjects();
        User emptyUser = new User();
        emptyUser.setUsername(" - ");
        emptyUser.setId(0);
        userList.add(emptyUser);
        model.addAttribute("users", userList);
        return "/Customer/newCustomer";
    }

    //route to create or update (post)
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String createOrUpdateCustomer(Customer customer) throws DomainObjectNotFound {
        int id = customerService.createOrUpdateObject(customer).getId();
        return "redirect:/customer/" + id;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
