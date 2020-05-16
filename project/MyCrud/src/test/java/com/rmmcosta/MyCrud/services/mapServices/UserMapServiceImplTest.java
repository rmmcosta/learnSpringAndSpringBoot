package com.rmmcosta.MyCrud.services.mapServices;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.DomainObject;
import com.rmmcosta.MyCrud.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("map")
class UserMapServiceImplTest {

    private UserMapServiceImpl userMapService;

    @BeforeEach
    void setUp() {
        userMapService = new UserMapServiceImpl();
    }

    @Test
    void listAllObjects() {
        assertEquals(1, userMapService.listAllObjects().size());
    }

    @Test
    void getObjectById() throws DomainObjectNotFound {
        List<User> userList = userMapService.listAllObjects();
        for (DomainObject object : userList) {
            User user = (User) object;
            assertEquals(user.getUsername(), userMapService.getObjectById(user.getId()).getUsername());
        }
    }

    @Test
    void createOrUpdateObject() throws DomainObjectNotFound {
        int initialSize = userMapService.getCount();
        User user = new User();
        int id = userMapService.getNextKey();
        user.setId(id);
        String username = "aramos";
        user.setUsername(username);
        user.setPassword("123456");
        userMapService.createOrUpdateObject(user);
        assertEquals(initialSize + 1, userMapService.getCount());
        assertEquals(username, userMapService.getObjectById(id).getUsername());
    }

    @Test
    void deleteObject() throws DomainObjectNotFound {
        List<User> userList = userMapService.listAllObjects();
        int initialSize = userMapService.getCount();
        userMapService.deleteObject(userList.get(0).getId());
        assertEquals(initialSize - 1, userMapService.getCount());
    }

    @Test
    void bootstrapObjects() {
        assertEquals(1, userMapService.domainObjectMap.size());
    }
}