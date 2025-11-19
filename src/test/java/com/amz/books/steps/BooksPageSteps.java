package com.amz.books.steps;

import com.amz.books.model.Book;
import com.amz.books.model.enums.SearchFilters;
import com.amz.books.pages.BooksPage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BooksPageSteps {

    private final BooksPage booksPage;
    private static final Logger logger = LoggerFactory.getLogger(BooksPageSteps.class);

    public BooksPageSteps(WebDriver driver) {
        this.booksPage = new BooksPage(driver);
    }

    public BooksPageSteps openBooksPage() {
        logger.info("Opening Books page");
        booksPage.open();
        return this;
    }

    public BooksPageSteps searchBook(String title) {
        logger.info("Searching for book: {}", title);
        booksPage.searchForBook(title);
        return this;
    }

    public BooksPageSteps applyFilter(SearchFilters.Format filterLabel) {
        logger.info("Applying filter: {}", filterLabel);
        booksPage.applySingleFilter(filterLabel);
        return this;
    }

    public BooksPageSteps addBookToCart(int index) {
        logger.info("Adding book at index {} to cart", index);
        booksPage.addBookToCart(index);
        return this;
    }

    public List<Book> getSearchResultsAsBooks() {
        return booksPage.getSearchResults().toBookList();
    }

    public boolean isFilterApplied(String filterLabel) {
        return booksPage.isFilterLabelBold(filterLabel);
    }

    public CheckoutPageSteps goToCartPageFromSidePanel() {
        booksPage.goToCartPageFromSidePanel();
        return new CheckoutPageSteps(booksPage.getDriver());
    }


}