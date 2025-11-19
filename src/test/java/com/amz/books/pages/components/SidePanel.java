package com.amz.books.pages.components;

import com.amz.books.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SidePanel extends BasePage {

    @FindBy(xpath = "//div[@id='ewc-content']")
    private WebElement sidePanel;

    @FindBy(xpath = "//div[@id='ewc-content']//div[contains(@class,'go-to-cart')]//a")
    private WebElement goToCartButton;

    @FindBy(xpath = "//div[@id='ewc-content']//span[@data-action='quantity']//span[@data-a-selector='value']")
    private WebElement quantityLabel;

    public SidePanel(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitForSidePanelToAppear() {
        waitForVisibility(sidePanel);
    }

    public void clickGoToCart() {
        click(goToCartButton);
    }

    public String getQuantityText() {
        return quantityLabel.getText().trim();
    }
}