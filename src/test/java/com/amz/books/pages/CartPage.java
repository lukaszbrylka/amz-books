package com.amz.books.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {

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

    public WebElement getOrderSummaryTitleElement() {
        return orderSummary.findElement(By.xpath("//span[@class='a-truncate-cut']"));
    }

    public void proceedToCheckout() {
        click(proceedToCheckoutButton);
    }
}