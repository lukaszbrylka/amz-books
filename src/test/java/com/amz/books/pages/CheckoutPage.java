package com.amz.books.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

    @FindBy(id = "ap_email_login")
    private WebElement emailInput;
    @FindBy(id = "continue")
    private WebElement continueButton;
    @FindBy(css = "div.a-box-inner")
    private WebElement checkoutSummary;
    @FindBy(id = "address-ui-widgets-enterAddressFullName")
    private WebElement fullNameInput;
    @FindBy(id = "address-ui-widgets-enterAddressLine1")
    private WebElement addressLine1Input;
    @FindBy(id = "address-ui-widgets-enterAddressCity")
    private WebElement cityInput;
    @FindBy(id = "address-ui-widgets-enterAddressPostalCode")
    private WebElement postalCodeInput;
    @FindBy(id = "address-ui-widgets-enterAddressCountryCode")
    private WebElement countryDropdown;
    @FindBy(name = "shipToThisAddress")
    private WebElement shipToThisAddressButton;
    @FindBy(css = "div.payment-section-disabled")
    private WebElement paymentSectionDisabled;
    @FindBy(id = "continue-bottom")
    private WebElement continueToPaymentButton;
    @FindBy(css = "div.address-section-disabled")
    private WebElement addressSectionDisabled;
    @FindBy(name = "addCreditCardNumber")
    private WebElement cardNumberInput;
    @FindBy(name = "ppw-expirationDate_month")
    private WebElement expiryMonthDropdown;
    @FindBy(name = "ppw-expirationDate_year")
    private WebElement expiryYearDropdown;
    @FindBy(name = "ppw-cvv")

    private WebElement cvvInput;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isOnPaymentPage() {
        try {
            return cardNumberInput.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterEmail(String email) {
        sendKeyboardInput(emailInput, email);
    }

    public void continueAsGuest() {
        clickElement(continueButton);
    }

    public boolean isEmailAccepted() {
        return checkoutSummary.isDisplayed();
    }

    public boolean isCheckoutSummaryCorrect() {
        return checkoutSummary.isDisplayed();
    }

    public void fillDeliveryAddress(String fullName, String address, String city, String postalCode, String country) {
        sendKeyboardInput(fullNameInput, fullName);
        sendKeyboardInput(addressLine1Input, address);
        sendKeyboardInput(cityInput, city);
        sendKeyboardInput(postalCodeInput, postalCode);
        new Select(countryDropdown).selectByVisibleText(country);
        clickElement(shipToThisAddressButton);
    }

    public boolean isAddressSaved() {
        return shipToThisAddressButton.isDisplayed(); // Adjust based on confirmation element
    }

    public boolean isPaymentSectionDisabled() {
        return paymentSectionDisabled.isDisplayed();
    }

    public void clickContinueToPayment() {
        clickElement(continueToPaymentButton);
    }

    public boolean areAddressSectionsDisabled() {
        return addressSectionDisabled.isDisplayed();
    }

    public void enterCardDetails(String cardNumber, String expiryMonth, String expiryYear, String cvv) {
        cardNumberInput.sendKeys(cardNumber);
        new Select(expiryMonthDropdown).selectByVisibleText(expiryMonth);
        new Select(expiryYearDropdown).selectByVisibleText(expiryYear);
        cvvInput.sendKeys(cvv);
    }

    public boolean isCardAccepted() {
        // You may need to check for confirmation or error message
        return true; // Placeholder
    }
}