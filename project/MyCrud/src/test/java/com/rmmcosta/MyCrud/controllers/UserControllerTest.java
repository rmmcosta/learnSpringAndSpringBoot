package com.rmmcosta.MyCrud.controllers;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.User;
import com.rmmcosta.MyCrud.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void listUsers() {
        try {
            List<User> userList = new ArrayList<>();
            userList.add(new User());
            when(userService.listAllObjects()).thenReturn((List) userList);
            mockMvc.perform(get("/users")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/User/users")).
                    andExpect(model().attribute("users", hasSize(1)));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void showUser() {
        int id = 10;
        String username = "user1";
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        try {
            when(userService.getObjectById(10)).thenReturn(user);
        } catch (DomainObjectNotFound domainObjectNotFound) {
            assertFalse(true);
        }
        try {
            mockMvc.perform(get("/user/10")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/User/user")).
                    andExpect(model().attribute("user", instanceOf(User.class))).
                    andExpect(model().attribute("user", hasProperty("id", is(id)))).
                    andExpect(model().attribute("user", hasProperty("username", is(username))));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void editUser() {
        try {
            mockMvc.perform(get("/user/edit/1")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/User/newUser"));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void newUser() {
        verifyNoInteractions(userService);
        try {
            mockMvc.perform(get("/user/new")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/User/newUser")).
                    andExpect(model().attribute("user", instanceOf(User.class)));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void createOrUpdateUser() {
        int id = 100;
        String username = "user1", password = "123456coiso";
        boolean isActive = false;
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setIsActive(isActive);

        try {
            when(userService.createOrUpdateObject(Mockito.any(user.getClass()))).thenReturn(user);
        } catch (DomainObjectNotFound domainObjectNotFound) {
            System.out.println("when error: " + domainObjectNotFound.getMessage());
            assertFalse(true);
        }
        try {
            mockMvc.perform(post("/user")
                    .param("id", String.valueOf(id))
                    .param("username", username)
                    .param("password", password)
                    .param("isActive", String.valueOf(isActive))).
                    andExpect(status().is3xxRedirection()).
                    andExpect(view().name("redirect:/user/" + id)).
                    andExpect(model().attribute("user", instanceOf(User.class))).
                    andExpect(model().attribute("user", hasProperty("username", is(username)))).
                    andExpect(model().attribute("user", hasProperty("password", is(password)))).
                    andExpect(model().attribute("user", hasProperty("isActive", is(isActive))));
            ;
        } catch (Exception e) {
            System.out.println("perform error:" + e.getMessage());
            assertFalse(true);
        }

        ArgumentCaptor<User> boundUser = ArgumentCaptor.forClass(User.class);
        try {
            verify(userService).createOrUpdateObject(boundUser.capture());
            assertEquals(id, boundUser.getValue().getId());
            assertEquals(username, boundUser.getValue().getUsername());
            assertEquals(password, boundUser.getValue().getPassword());
            assertEquals(isActive, boundUser.getValue().getIsActive());
        } catch (DomainObjectNotFound domainObjectNotFound) {
            System.out.println("error bounding: " + domainObjectNotFound.getMessage());
            assertFalse(true);
        }
    }

    @Test
    void deleteUser() {
        int id = 1;
        try {
            mockMvc.perform(get("/user/delete/" + id)).
                    andExpect(status().isFound()).
                    andExpect(view().name("redirect:/users"));
        } catch (Exception e) {
            assertFalse(true);
        }
        try {
            verify(userService, times(1)).deleteObject(id);
        } catch (DomainObjectNotFound domainObjectNotFound) {
            System.out.println("error verifying delete: " + domainObjectNotFound.getMessage());
            assertFalse(true);
        }
    }
}