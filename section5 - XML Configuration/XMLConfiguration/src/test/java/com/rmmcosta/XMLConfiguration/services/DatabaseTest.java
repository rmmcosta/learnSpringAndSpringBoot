package com.rmmcosta.XMLConfiguration.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:spring/oracle-config.xml"})
class DatabaseTest {

    @Autowired
    Database database;

    @Test
    void open() {
        int result = database.open();
        assertEquals(1, result);
    }
}