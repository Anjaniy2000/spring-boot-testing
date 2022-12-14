package com.anjaniy.springboottesting.integration;

import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractContainerBaseTest {

    private static final MySQLContainer mySQLContainer;

    static {
        mySQLContainer = new MySQLContainer("mysql:latest")
                .withDatabaseName("employee-management-system")
                .withUsername("username")
                .withPassword("password");

        mySQLContainer.start();

    }
}
