package com.amz.books.pages.components;

import com.amz.books.pages.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NavBar extends BasePage {

    @FindBy(xpath = "//div[contains(@class,'subnavBar')]/div/a")
    private WebElement currentCategory;
    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    private WebElement searchAmazon;
    @FindBy(xpath = "//input[@id='nav-search-submit-button']")
    private WebElement searchButton;
    @FindBy(xpath = "//span[@id='nav-cart-count']")
    private WebElement cartCount;
    @FindBy(xpath = "//a[@id='nav-cart']")
    private WebElement cartIcon;

    private int shopCartValue = 0;

    private static final Logger logger = LoggerFactory.getLogger(NavBar.class);

    public NavBar(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getCurrentSubPageTitle() {
        waitForVisibility(currentCategory);
        String subPageTitle = currentCategory.getAttribute("aria-label").trim().toLowerCase();
        logger.info("Opened: {}", subPageTitle);
        return subPageTitle;
    }

    public void searchForItem(String itemTitle) {
        waitForVisibility(searchAmazon);
        searchAmazon.clear();
        searchAmazon.sendKeys(itemTitle);
        logger.info("Searching for item: {}", itemTitle);
        clickElement(searchButton);
    }

    public void setShoppingCartValue() {
        shopCartValue = Integer.parseInt(cartCount.getText().trim());
    }

    public int getShoppingCartValue() {
        return shopCartValue;
    }


    public void goToShoppingCart() {
        logger.info("Navigating to shopping cart");
        clickElement(cartIcon);
    }


    public boolean isCartValueIncreased() {
        try {
            return wait.until(driver -> {
                try {
                    int newCartValue = Integer.parseInt(cartCount.getText().trim());
                    boolean added = newCartValue > shopCartValue;
                    logger.info("Item added to cart: {}", added);
                    return added;
                } catch (NumberFormatException e) {
                    logger.warn("Failed to parse cart value", e);
                    return false;
                }
            });
        } catch (TimeoutException e) {
            logger.warn("Timeout while waiting for book to be added to cart", e);
            return false;
        }
    }


}