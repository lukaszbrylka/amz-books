package com.amz.books.driver;

import com.amz.books.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private static DriverManager instance;
    private WebDriver driver;

    private DriverManager() {
        String BROWSER = ConfigReader.get("browser");
        if (BROWSER.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else throw new IllegalStateException("Browser: " + BROWSER + " is not currently supported");
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }


    public static WebDriver driver() {
        return getInstance().getDriver();
    }


    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            instance = null;
        }
    }
}