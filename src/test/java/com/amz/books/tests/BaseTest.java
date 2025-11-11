package com.amz.books.tests;

import com.amz.books.config.ConfigReader;
import com.amz.books.driver.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {
    protected static final String BASE_URL = ConfigReader.get("amz.books.url");

    @BeforeAll
    static void setUp() {
        DriverManager.driver().manage().window().maximize();
        DriverManager.driver().get(BASE_URL);
    }

    @AfterAll
    static void tearDown() {
        DriverManager.getInstance().quitDriver();
    }
}