package com.amz.books.pages;

import com.amz.books.model.Book;
import com.amz.books.model.enums.SearchFilters;
import com.amz.books.pages.components.NavBar;
import com.amz.books.pages.components.SidePanel;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.ArrayList;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BooksPage extends BasePage {
    private final NavBar navBar;
    private final SidePanel sidePanel;

    private static final Logger logger = LoggerFactory.getLogger(BooksPage.class);

    //Search results elements xPaths
    private static final String BOOK_TITLE_XPATH = "//div[@data-cy='title-recipe']//h2";
    private static final String BOOK_IS_PAPERBACK_XPATH = "//div[@data-cy='price-recipe']//a[contains(.,'Paperback')]";
    private static final String BOOK_PUBLISH_DATE_XPATH = "//div[@class='a-row']/span[@class='a-size-base a-color-secondary a-text-normal']";
    private static final String BOOK_ADD_TO_CART_XPATH_TO_FORMAT = "(//div[@data-component-type='s-search-result'])[%d]//button[@name='submit.addToCart']";
    private static final String SEARCH_RESULTS_FILTER_XPATH_TO_FORMAT = "//span[text()='%s']";

    @FindBy(xpath = "//div[@data-component-type='s-search-result']")
    private List<WebElement> searchResults;
    @FindBy(xpath = "//span[@data-component-type='s-result-info-bar']")
    private WebElement resultsContainer;

    private List<Book> currentSearchResults;

    public BooksPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.navBar = new NavBar(driver);
        this.sidePanel = new SidePanel(driver);
    }

    public NavBar getNavBar() {
        return navBar;
    }

    public SidePanel getSidePanel() {
        return sidePanel;
    }

    public String getCurrentSubPageTitle() {
        return getNavBar().getCurrentSubPageTitle();
    }


    public BooksPage searchForBookByTitle(String bookTitle) {
        navBar.searchForItem(bookTitle);
        waitForVisibility(resultsContainer);
        setSearchResults();
        logger.info("Search completed for book title: '{}'. Found {} results.", bookTitle, currentSearchResults.size());
        return this;
    }


    public BooksPage addBookToCart() {
        return addBookToCart(1);
    }


    public BooksPage addBookToCart(int bookIndex) {
        setShoppingCartValue();
        String xpath = String.format(BOOK_ADD_TO_CART_XPATH_TO_FORMAT, bookIndex);
        WebElement addToCartButton = driver.findElement(By.xpath(xpath));
        waitForElementToBeClickable(addToCartButton);
        addToCartButton.click();
        logger.info("Clicked 'Add to Cart' for book at index {}", bookIndex);
        sidePanel.waitForSidePanelToAppear();
        return this;
    }

    public CartPage goToCartPageViaOpenedSidePanel() {
        return sidePanel.clickGoToCart();
    }

    public boolean isCartValueIncreased() {
        return navBar.isCartValueIncreased();
    }


    private void applySingleFilterByLabel(String filterName) {
        String xpath = String.format(SEARCH_RESULTS_FILTER_XPATH_TO_FORMAT, filterName);
        WebElement singleFilter = driver.findElement(By.xpath(xpath));
        waitForElementToBeClickable(singleFilter);
        singleFilter.click();
        logger.info("Applied filter: '{}'", filterName);
    }


    public BooksPage applySingleFilter(SearchFilters.Format format) {
        applySingleFilterByLabel(format.getLabel());
        return this;
    }

    public BooksPage applySingleFilter(SearchFilters.Language language) {
        applySingleFilterByLabel(language.getLabel());
        return this;
    }

    public BooksPage applySingleFilter(SearchFilters.Condition condition) {
        applySingleFilterByLabel(condition.getLabel());
        return this;
    }


    private boolean isFilterApplied(String filterName) {
        String xpath = String.format(SEARCH_RESULTS_FILTER_XPATH_TO_FORMAT, filterName);

        try {
            boolean applied = wait.until(driver -> {
                WebElement filterLabel = driver.findElement(By.xpath(xpath));
                String classes = filterLabel.getAttribute("class");
                return classes.contains("a-text-bold");
            });
            if (applied) {
                waitForVisibility(resultsContainer);
                setSearchResults();
                logger.info("Filter '{}' is applied. Search results updated.", filterName);
            } else {
                logger.info("Filter '{}' is not applied.", filterName);
            }
            return applied;
        } catch (TimeoutException e) {
            logger.warn("Timeout while checking if filter '{}' is applied.", filterName);
            return false;
        }
    }

    public boolean isFilterApplied(SearchFilters.Format format) {
        return isFilterApplied(format.getLabel());
    }

    public boolean isFilterApplied(SearchFilters.Language language) {
        return isFilterApplied(language.getLabel());
    }

    public boolean isFilterApplied(SearchFilters.Condition condition) {
        return isFilterApplied(condition.getLabel());
    }

    private void setShoppingCartValue() {
        navBar.setShoppingCartValue();
    }

    private int getShoppingCartValue() {
        return navBar.getShoppingCartValue();
    }

    private int getCurrentPageSearchResultsCount() {
        return searchResults.size();
    }

    public List<Book> getSearchResults() {
        return currentSearchResults;
    }


    private void setSearchResults() {
        List<WebElement> bookElements = searchResults;
        currentSearchResults = new ArrayList<>();

        for (WebElement book : bookElements) {
            String title;
            String publishDate;
            boolean isPaperback;

            try {
                title = book.findElement(By.xpath(BOOK_TITLE_XPATH)).getText().trim();
            } catch (NoSuchElementException e) {
                title = "N/A";
            }

            try {
                publishDate = book.findElement(By.xpath(BOOK_PUBLISH_DATE_XPATH)).getText().trim();
            } catch (NoSuchElementException e) {
                publishDate = "N/A";
            }

            try {
                isPaperback = !book.findElements(By.xpath(BOOK_IS_PAPERBACK_XPATH)).isEmpty();
            } catch (NoSuchElementException e) {
                isPaperback = false;
            }

            currentSearchResults.add(new Book(title, publishDate, isPaperback));
        }

        logger.info("Parsed {} books from current search results.", currentSearchResults.size());
    }
}

