package com.amz.books.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;
    protected final JavascriptExecutor jsExecutor;

    private final static Duration SHORT_WAIT = Duration.ofSeconds(5);
    private final static Duration MEDIUM_WAIT = Duration.ofSeconds(10);
    private final static Duration LONG_WAIT = Duration.ofSeconds(30);

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, MEDIUM_WAIT);
        this.actions = new Actions(driver);
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void click(WebElement element) {
        waitForClickable(element);
        element.click();
    }

    protected void doubleClick(WebElement element) {
        waitForClickable(element);
        actions.doubleClick(element).perform();
    }

    protected void type(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        waitForVisibility(element);
        return element.getText().trim();
    }

    protected String getValue(WebElement element) {
        waitForVisibility(element);
        return element.getAttribute("value");
    }

    protected void jsClick(WebElement element) {
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    protected void dragAndDrop(WebElement source, WebElement target) {
        waitForVisibility(source);
        waitForVisibility(target);
        actions.dragAndDrop(source, target).perform();
    }

}