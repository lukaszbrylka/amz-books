package com.amz.books.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//input[@id='ap_email_login']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//div[contains(@class,'a-box-inner')]")
    private WebElement checkoutSummary;

    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressFullName']")
    private WebElement fullNameInput;

    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressLine1']")
    private WebElement addressLine1Input;

    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressCity']")
    private WebElement cityInput;

    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressPostalCode']")
    private WebElement postalCodeInput;

    @FindBy(xpath = "//select[@id='address-ui-widgets-enterAddressCountryCode']")
    private WebElement countryDropdown;

    @FindBy(xpath = "//input[@name='shipToThisAddress']")
    private WebElement shipToThisAddressButton;

    @FindBy(xpath = "//input[@id='continue-bottom']")
    private WebElement continueToPaymentButton;

    @FindBy(xpath = "//input[@name='addCreditCardNumber']")
    private WebElement cardNumberInput;

    @FindBy(xpath = "//select[@name='ppw-expirationDate_month']")
    private WebElement expiryMonthDropdown;

    @FindBy(xpath = "//select[@name='ppw-expirationDate_year']")
    private WebElement expiryYearDropdown;

    @FindBy(xpath = "//input[@name='ppw-cvv']")
    private WebElement cvvInput;

    @FindBy(xpath = "//input[@id='confirm-card-id']")
    private WebElement applyCardButton;

    // Additional locators for verification
    @FindBy(xpath = "//div[@id='payment-section' and contains(@class,'disabled')]")
    private WebElement paymentSectionDisabled;

    @FindBy(xpath = "//div[@id='address-section' and contains(@class,'disabled')]")
    private WebElement addressSectionsDisabled;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        type(emailInput, email);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public WebElement getCheckoutSummaryElement() {
        return checkoutSummary;
    }

    public void fillDeliveryAddress(String fullName, String address, String city, String postalCode, String country) {
        type(fullNameInput, fullName);
        type(addressLine1Input, address);
        type(cityInput, city);
        type(postalCodeInput, postalCode);
        new Select(countryDropdown).selectByVisibleText(country);
        click(shipToThisAddressButton);
    }

    public void clickContinueToPayment() {
        click(continueToPaymentButton);
    }

    public void enterCardDetails(String cardNumber, String expiryMonth, String expiryYear, String cvv) {
        type(cardNumberInput, cardNumber);
        new Select(expiryMonthDropdown).selectByVisibleText(expiryMonth);
        new Select(expiryYearDropdown).selectByVisibleText(expiryYear);
        type(cvvInput, cvv);
    }

    public WebElement getApplyCardButton() {
        return applyCardButton;
    }

    public boolean isOrderSummaryVisible() {
        return checkoutSummary.isDisplayed();
    }

    public boolean isPaymentSectionDisabled() {
        return paymentSectionDisabled.isDisplayed();
    }

    public boolean areAddressSectionsDisabled() {
        return addressSectionsDisabled.isDisplayed();
    }
}