package com.surajswamy.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestConfig {
    private static final Properties PROPERTIES = loadProperties();

    private TestConfig() {
    }

    public static String baseUrl() {
        return System.getProperty("base.url", PROPERTIES.getProperty("base.url"));
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream input = TestConfig.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("config.properties was not found");
            }
            properties.load(input);
            return properties;
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load test configuration", exception);
        }
    }
}
