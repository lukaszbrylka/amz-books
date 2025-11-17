
package com.amz.books.steps;

import com.amz.books.pages.CheckoutPage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckoutPageSteps {

    private final CheckoutPage checkoutPage;
    private static final Logger logger = LoggerFactory.getLogger(CheckoutPageSteps.class);

    public CheckoutPageSteps(WebDriver driver) {
        this.checkoutPage = new CheckoutPage(driver);
    }

    public CheckoutPageSteps enterEmail(String email) {
        logger.info("Entering email: {}", email);
        checkoutPage.enterEmail(email);
        checkoutPage.clickContinue();
        return this;
    }

    public CheckoutPageSteps fillAddress(String name, String street, String city, String zip, String country) {
        logger.info("Filling delivery address");
        checkoutPage.fillDeliveryAddress(name, street, city, zip, country);
        return this;
    }

    public CheckoutPageSteps continueToPayment() {
        logger.info("Continuing to payment");
        checkoutPage.clickContinueToPayment();
        return this;
    }

    public CheckoutPageSteps enterCardDetails(String cardNumber, String expMonth, String expYear, String cvv) {
        logger.info("Entering card details");
        checkoutPage.enterCardDetails(cardNumber, expMonth, expYear, cvv);
        return this;
    }

    public boolean isOrderSummaryVisible() {
        return checkoutPage.getCheckoutSummaryElement().isDisplayed();
    }

    public boolean isCheckoutSummaryVisible() {
        return checkoutPage.getCheckoutSummaryElement().isDisplayed();
    }

    public boolean isPaymentSectionDisabled() {
        return checkoutPage.isPaymentSectionDisabled();
    }

    public boolean areAddressSectionsDisabled() {
        return checkoutPage.areAddressSectionsDisabled();
    }
}
