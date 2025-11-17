package com.amz.books.pages;

import com.amz.books.model.enums.SearchFilters;
import com.amz.books.pages.components.NavBar;
import com.amz.books.pages.components.SearchResults;
import com.amz.books.pages.components.SidePanel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BooksPage extends BasePage {
    private final NavBar navBar;
    private final SidePanel sidePanel;

    private static final Logger logger = LoggerFactory.getLogger(BooksPage.class);


    private static final String BOOK_ADD_TO_CART_XPATH_TO_FORMAT = "(//div[@data-component-type='s-search-result'])[%d]//button[@name='submit.addToCart']";
    private static final String SEARCH_RESULTS_FILTER_XPATH_TO_FORMAT = "//span[text()='%s']";

    @FindBy(xpath = "//div[@data-component-type='s-search-result']")
    private List<WebElement> searchResults;

    public BooksPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.navBar = new NavBar(driver);
        this.sidePanel = new SidePanel(driver);
    }

    public void open() {
        driver.get("https://www.amazon.com/books");
    }

    public void searchForBook(String title) {
        navBar.searchForItem(title);
    }

    public void addBookToCart() {
        addBookToCart(1);
    }

    public void addBookToCart(int bookIndex) {
        String xpath = String.format(BOOK_ADD_TO_CART_XPATH_TO_FORMAT, bookIndex);
        WebElement addToCartButton = driver.findElement(By.xpath(xpath));
        waitForClickable(addToCartButton);
        addToCartButton.click();
        logger.info("Clicked 'Add to Cart' for book at index {}", bookIndex);
        sidePanel.waitForSidePanelToAppear();
    }

    private void applySingleFilterByLabel(String filterName) {
        String xpath = String.format(SEARCH_RESULTS_FILTER_XPATH_TO_FORMAT, filterName);
        WebElement singleFilter = driver.findElement(By.xpath(xpath));
        waitForClickable(singleFilter);
        singleFilter.click();
        logger.info("Applied filter: '{}'", filterName);
    }


    public void applySingleFilter(SearchFilters.Format format) {
        applySingleFilterByLabel(format.getLabel());
    }

    public void applySingleFilter(SearchFilters.Language language) {
        applySingleFilterByLabel(language.getLabel());
    }

    public void applySingleFilter(SearchFilters.Condition condition) {
        applySingleFilterByLabel(condition.getLabel());
    }

    public boolean isFilterLabelBold(String filterLabel) {
        String xpath = String.format(SEARCH_RESULTS_FILTER_XPATH_TO_FORMAT, filterLabel);
        try {
            WebElement filterElement = driver.findElement(By.xpath(xpath));
            return filterElement.getAttribute("class").contains("a-text-bold");
        } catch (Exception e) {
            return false;
        }
    }

    public SearchResults getSearchResults() {
        return new SearchResults(searchResults);
    }

    public void goToCartPageFromSidePanel() {
        sidePanel.clickGoToCart();
    }
}