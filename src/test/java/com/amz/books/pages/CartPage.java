package com.amz.books.pages;

import com.amz.books.model.Book;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(CartPage.class);


    @FindBy(xpath = "//div[@class='sc-list-item-content']")
    private WebElement cartItem;
    @FindBy(xpath = "//div[@id='sc-active-cart']")
    private WebElement orderSummary;
    @FindBy(xpath = "//input[@data-feature-id='proceed-to-checkout-action']")
    private WebElement proceedToCheckoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isCartPageDisplayed() {
        return cartItem.isDisplayed();
    }


    public boolean isOrderSummaryDetailsCorrect(Book bookData) {
        String expectedTitle = bookData.getTitle().trim();
        String actualTitle;

        try {
            WebElement titleElement = orderSummary.findElement(By.xpath("//span[@class='a-truncate-cut']"));
            actualTitle = titleElement.getText().trim();

            logger.info("Comparing expected title '{}' with actual title '{}'", expectedTitle, actualTitle);

            boolean match = actualTitle.equalsIgnoreCase(expectedTitle);
            logger.info("Order summary title match result: {}", match);
            return match;

        } catch (NoSuchElementException e) {
            logger.warn("Order summary title element not found", e);
            return false;
        }
    }

    public CheckoutPage proceedToCheckout() {
        clickElement(proceedToCheckoutButton);
        return new CheckoutPage(driver);
    }
}


