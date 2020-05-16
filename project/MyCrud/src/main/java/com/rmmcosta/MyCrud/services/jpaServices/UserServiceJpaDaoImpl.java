package com.rmmcosta.MyCrud.services.jpaServices;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.domain.User;
import com.rmmcosta.MyCrud.services.CustomerService;
import com.rmmcosta.MyCrud.services.UserService;
import com.rmmcosta.MyCrud.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
@Profile("jpadao")
public class UserServiceJpaDaoImpl extends AbstractJpaService implements UserService {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EncryptionService encryptionService;

    @Override
    public List<User> listAllObjects() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getObjectById(int id) throws DomainObjectNotFound {
        return entityManager.find(User.class, id);
    }

    @Override
    public User createOrUpdateObject(User object) throws DomainObjectNotFound {
        if (!object.getPassword().isEmpty()) {
            object.setEncryptedPassword(encryptionService.encryptPassword(object.getPassword()));
        }
        entityManager.getTransaction().begin();
        User newUser = entityManager.merge(object);
        entityManager.getTransaction().commit();
        return newUser;
    }

    @Override
    public void deleteObject(int id) throws DomainObjectNotFound {
        User user = entityManager.find(User.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    int getCount() {
        return entityManager.createNativeQuery("Select count(1) from User").getFirstResult();
    }
}
