package com.amz.books.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ActionsDemoPage extends BasePage {

    public ActionsDemoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='action1']")
    private WebElement singleClickButton;

    @FindBy(xpath = "//span[@id='action1Status']")
    private WebElement singleClickStatus;

    @FindBy(xpath = "//input[@id='action1A']")
    private WebElement doubleClickButton;

    @FindBy(xpath = "//span[@id='action1AStatus']")
    private WebElement doubleClickStatus;

    @FindBy(xpath = "//input[@id='action3']")
    private WebElement keyboardInput;

    @FindBy(xpath = "//ul[@id='sortable1']/li")
    private List<WebElement> leftColumnItems;

    @FindBy(xpath = "//ul[@id='sortable2']/li")
    private List<WebElement> rightColumnItems;

    @FindBy(xpath = "//ul[@id='sortable2']")
    private WebElement rightColumn;


    public void singleClickButton() {
        jsClick(singleClickButton);
    }

    public String getSingleClickStatusText() {
        return getText(singleClickStatus);
    }

    public void doubleClickButton() {
        doubleClick(doubleClickButton);
    }

    public String getDoubleClickStatusText() {
        return getText(doubleClickStatus);
    }

    public void typeIntoTestInput(String text) {
        type(keyboardInput, text);
    }

    public String getTestInputText() {
        return getValue(keyboardInput);
    }


    public void moveAllItemsFromLeftToRightColumn() {
        for (WebElement item : leftColumnItems) {
            waitForVisibility(item);
            dragAndDrop(item, rightColumn);
        }
    }


    public List<WebElement> getLeftColumnItems() {
        return leftColumnItems;
    }

    public List<WebElement> getRightColumnItems() {
        return rightColumnItems;
    }


}