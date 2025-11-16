package com.amz.books.pages.components;

import com.amz.books.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public NavBar(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getCurrentSubPageTitle() {
        waitForVisibility(currentCategory);
        return currentCategory.getAttribute("aria-label").trim().toLowerCase();
    }

    public void searchForItem(String itemTitle) {
        waitForVisibility(searchAmazon);
        searchAmazon.clear();
        searchAmazon.sendKeys(itemTitle);
        click(searchButton);
    }

    public String getCartCountText() {
        return cartCount.getText().trim();
    }

    public void clickCartIcon() {
        click(cartIcon);
    }
}