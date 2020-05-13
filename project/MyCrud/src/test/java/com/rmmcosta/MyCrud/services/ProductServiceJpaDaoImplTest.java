package com.rmmcosta.MyCrud.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceJpaDaoImplTest {

    private ProductServiceJpaDaoImpl productServiceJpaDao;

    @BeforeEach
    void setup() {
        productServiceJpaDao = new ProductServiceJpaDaoImpl();
    }

    @Test
    void listAllObjects() {
    }

    @Test
    void getObjectById() {
    }

    @Test
    void createOrUpdateObject() {
    }

    @Test
    void deleteObject() {
    }

    @Test
    void setEntityManagerFactory() {
    }
}