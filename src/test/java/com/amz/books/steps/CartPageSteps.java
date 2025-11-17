package com.amz.books.steps;

import com.amz.books.pages.CartPage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartPageSteps {

    private final CartPage cartPage;
    private static final Logger logger = LoggerFactory.getLogger(CartPageSteps.class);

    public CartPageSteps(WebDriver driver) {
        this.cartPage = new CartPage(driver);
    }

    public void isCartPageDisplayed() {
        logger.info("Checking visibility of Cart Page");
        cartPage.isCartPageDisplayed();
    }

    public CheckoutPageSteps proceedToCheckout() {
        logger.info("Proceeding to checkout");
        cartPage.proceedToCheckout();
        return new CheckoutPageSteps(cartPage.getDriver());
    }

    public String getOrderSummaryTitle() {
        logger.info("Fetching Order Summary title from Cart Page");
        return cartPage.getOrderSummaryTitleElement().getText();
    }
}
