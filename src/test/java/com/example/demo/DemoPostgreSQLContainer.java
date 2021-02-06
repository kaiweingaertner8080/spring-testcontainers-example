package com.example.demo;

import org.testcontainers.containers.PostgreSQLContainer;

public class DemoPostgreSQLContainer extends PostgreSQLContainer<DemoPostgreSQLContainer> {
    public static final String IMAGE_NAME = "postgres:12.4-alpine";

    private static DemoPostgreSQLContainer container;

    private DemoPostgreSQLContainer() {
        super(IMAGE_NAME);
    }

    public static DemoPostgreSQLContainer getInstance() {
        if (container == null) {
            container = new DemoPostgreSQLContainer();
        }
        return container;
    }
}
