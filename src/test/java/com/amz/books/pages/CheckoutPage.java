package com.amz.books.pages;

import org.openqa.selenium.TimeoutException;
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
    @FindBy(xpath = "//div[contains(@class,'payment-section-disabled')]")
    private WebElement paymentSectionDisabled;
    @FindBy(xpath = "//input[@id='continue-bottom']")
    private WebElement continueToPaymentButton;
    @FindBy(xpath = "//div[contains(@class,'address-section-disabled')]")
    private WebElement addressSectionDisabled;
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
        return shipToThisAddressButton.isDisplayed();
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
        try {
            waitForElementToBeEnabled(applyCardButton);
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }
}