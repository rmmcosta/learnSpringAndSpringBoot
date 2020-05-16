package com.rmmcosta.MyCrud.bootstrap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BootstrapUsersTest {

    @Test
    void getBootstrapUsers() {
        assertEquals(1, BootstrapUsers.getBootstrapUsers().size());
    }
}