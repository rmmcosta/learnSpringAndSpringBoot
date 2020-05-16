package com.rmmcosta.MyCrud.services.mapServices;

import com.rmmcosta.MyCrud.bootstrap.BootstrapUsers;
import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.User;
import com.rmmcosta.MyCrud.services.UserService;
import com.rmmcosta.MyCrud.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@Profile("map")
public class UserMapServiceImpl extends AbstractMapService implements UserService {
    private EncryptionService encryptionService;

    public UserMapServiceImpl() {
        super();
    }

    @Override
    public List<User> listAllObjects() {
        return (List<User>) super.listAllObjects();
    }

    @Override
    public User getObjectById(int id) throws DomainObjectNotFound {
        return (User) super.getObjectById(id);
    }

    @Override
    public User createOrUpdateObject(User object) throws DomainObjectNotFound {
        if (!object.getPassword().isEmpty() && encryptionService != null) {
            object.setEncryptedPassword(encryptionService.encryptPassword(object.getPassword()));
        }
        return (User) super.createOrUpdateObject(object);
    }

    @Override
    public void deleteObject(int id) throws DomainObjectNotFound {
        super.deleteObject(id);
    }

    @Override
    protected void bootstrapObjects() {
        domainObjectMap = new HashMap<>();
        for (User user : BootstrapUsers.getBootstrapUsers()) {
            domainObjectMap.put(user.getId(), user);
        }
    }

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }
}
