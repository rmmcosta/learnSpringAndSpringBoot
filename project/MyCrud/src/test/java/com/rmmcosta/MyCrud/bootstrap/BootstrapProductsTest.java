package com.rmmcosta.MyCrud.bootstrap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BootstrapProductsTest {

    @Test
    void getBootstrapProducts() {
        assertEquals(4, BootstrapProducts.getBootstrapProducts().size());
    }
}