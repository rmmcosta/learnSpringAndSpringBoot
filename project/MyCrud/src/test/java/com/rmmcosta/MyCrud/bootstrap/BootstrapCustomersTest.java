package com.rmmcosta.MyCrud.bootstrap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BootstrapCustomersTest {

    @Test
    void getBootstrapCustomers() {
        assertEquals(2, BootstrapCustomers.getBootstrapCustomers().size());
    }
}