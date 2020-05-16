package com.rmmcosta.MyCrud.services.jpaServices;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.User;
import com.rmmcosta.MyCrud.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("jpadao")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class UserServiceJpaDaoImplTest {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    void listAllObjects() {
        AbstractJpaService jpaService = (AbstractJpaService) userService;
        assertEquals(jpaService.getCount(), userService.listAllObjects().size());
    }

    @Test
    void getObjectById() throws DomainObjectNotFound {
        User user = new User();
        String username = "aramos";
        user.setUsername(username);
        user.setPassword("123456");
        User newUser = userService.createOrUpdateObject(user);
        assertEquals(user.getUsername(), userService.getObjectById(newUser.getId()).getUsername());
        userService.deleteObject(newUser.getId());
    }

    @Test
    void createOrUpdateObject() throws DomainObjectNotFound {
        int initialSize = userService.listAllObjects().size();
        User user = new User();
        String username = "aramos";
        user.setUsername(username);
        user.setPassword("123456");
        User newUser = userService.createOrUpdateObject(user);
        assertEquals(username, newUser.getUsername());
        assertEquals(initialSize + 1, userService.listAllObjects().size());
        assertEquals(username, userService.getObjectById(newUser.getId()).getUsername());
        assert !newUser.getEncryptedPassword().isEmpty();
        userService.deleteObject(newUser.getId());
    }

    @Test
    void deleteObject() throws DomainObjectNotFound {
        List<User> userList = (List<User>) userService.listAllObjects();
        int initialSize = userList.size();
        userService.deleteObject(userList.get(0).getId());
        assertEquals(initialSize - 1, userService.listAllObjects().size());
    }
}