package com.rmmcosta.MyCrud.controllers;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.User;
import com.rmmcosta.MyCrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.listAllObjects());
        return "/User/users";
    }

    @RequestMapping("/user/{id}")
    public String showUser(Model model, @PathVariable int id) throws DomainObjectNotFound {
        model.addAttribute("user", userService.getObjectById(id));
        return "/User/user";
    }

    @RequestMapping("/user/edit/{id}")
    public String editUser(Model model, @PathVariable int id) throws DomainObjectNotFound {
        model.addAttribute("user", userService.getObjectById(id));
        return "/User/newUser";
    }

    @RequestMapping("/user/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/User/newUser";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String createOrUpdateUser(User user) throws DomainObjectNotFound {
        int id = userService.createOrUpdateObject(user).getId();
        return "redirect:/user/" + id;
    }

    @RequestMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable int id) throws DomainObjectNotFound {
        userService.deleteObject(id);
        return "redirect:/users";
    }
}
