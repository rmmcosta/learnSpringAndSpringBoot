package com.rmmcosta.MyCrud.bootstrap;

import com.rmmcosta.MyCrud.domain.User;

import java.util.ArrayList;
import java.util.List;

public class BootstrapUsers {
    public static List<User> getBootstrapUsers() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setUsername("rmmcosta");
        user.setPassword("123456");
        userList.add(user);
        return userList;
    }
}
