package com.amz.books.tests;

import com.amz.books.pages.ActionsDemoPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ActionsDemoTest {

    private WebDriver driver;
    private ActionsDemoPage actionsPage;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.testautomationstudio.com/demo/actions/");
        actionsPage = new ActionsDemoPage(driver);
    }

    @Test
    @Order(1)
    public void testSingleClick() {
        actionsPage.singleClickButton();
        String expectedText = "Action 1 Click";
        String actualText = actionsPage.getSingleClickStatusText();
        assertEquals(expectedText, actualText, "Button not clicked or text has changed");
    }

    @Test
    @Order(2)
    public void testDoubleClick() {
        actionsPage.doubleClickButton();
        String expectedText = "Action 1 Double Click";
        String actualText = actionsPage.getDoubleClickStatusText();
        assertEquals(expectedText, actualText, "Button not clicked or text has changed");
    }

    @Test
    @Order(3)
    public void testKeyboardAction() {
        String expectedText = "Hello from JUnit!";
        actionsPage.typeIntoTestInput(expectedText);
        String actualText = actionsPage.getTestInputText();
        assertEquals(expectedText, actualText, "Input text not match with typed one");

    }


    @Test
    @Order(4)
    public void testDragAndDropAllItems() {
        int initialRightCount = actionsPage.getRightColumnItems().size();
        int leftCount = actionsPage.getLeftColumnItems().size();

        actionsPage.moveAllItemsFromLeftToRightColumn();

        assertTrue(actionsPage.getLeftColumnItems().isEmpty(), "Left column should be empty after moving items!");

        List<WebElement> rightItems = actionsPage.getRightColumnItems();
        assertEquals(initialRightCount + leftCount, rightItems.size(), "Right column does not have expected number of items!");

        long grayCount = rightItems.stream()
                .filter(element -> element.getAttribute("class").contains("ui-state-default"))
                .count();

        long yellowCount = rightItems.stream()
                .filter(element -> element.getAttribute("class").contains("ui-state-highlight"))
                .count();

        assertEquals(5, grayCount, "Expected 5 gray items in the right column after move!");
        assertEquals(5, yellowCount, "Expected 5 yellow items in the right column after move!");
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}