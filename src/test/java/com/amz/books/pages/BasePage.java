package com.amz.books.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;
    protected final JavascriptExecutor jsExecutor;

    protected final Duration SHORT_WAIT = Duration.ofSeconds(5);
    protected final Duration MEDIUM_WAIT = Duration.ofSeconds(10);
    protected final Duration LONG_WAIT = Duration.ofSeconds(30);

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, SHORT_WAIT);
        this.actions = new Actions(driver);
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementToBeEnabled(WebElement element) {
        wait.until(driver -> element.isEnabled());
    }

    protected void clickElement(WebElement element) {
        waitForElementToBeClickable(element);
        actions.moveToElement(element).click().perform();
    }

    protected void doubleClickElement(WebElement element) {
        waitForElementToBeClickable(element);
        actions.doubleClick(element).perform();
    }


    protected void dragAndDrop(WebElement source, WebElement target) {
        waitForVisibility(source);
        waitForVisibility(target);
        actions.dragAndDrop(source, target).perform();
    }

    protected void sendKeyboardInput(WebElement element, String text) {
        waitForVisibility(element);
        actions.moveToElement(element).click().sendKeys(text).perform();
    }

    protected String getElementText(WebElement element) {
        waitForVisibility(element);
        return element.getText().trim();
    }

    protected String getInputElementText(WebElement element) {
        waitForVisibility(element);
        return jsExecutor.executeScript("return arguments[0].value;", element).toString();
    }


    protected void jsClick(WebElement element) {
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    protected WebElement findElementByJS(String selector) {
        return (WebElement) jsExecutor.executeScript("return document.querySelector(arguments[0]);", selector);
    }
}