package com.amz.books.tests;

import com.amz.books.model.Book;
import com.amz.books.model.enums.SearchFilters;
import com.amz.books.steps.BooksPageSteps;
import com.amz.books.driver.DriverManager;
import com.amz.books.utils.assertions.BookAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookSearchTests extends BaseTest {

    private BooksPageSteps booksSteps;
    private List<Book> searchResults;
    private String bookTitle;

    @BeforeEach
    void initSteps() {
        booksSteps = new BooksPageSteps(DriverManager.getInstance().getDriver());
    }

    // this is NOT example of "Chain of responsibility" pattern, it is just method chaining.
    @Test
    void shouldSearchAndApplyFilterScenario() {
        bookTitle = "thinking in java";

        booksSteps.openBooksPage()
                .searchBook(bookTitle)
                .applyFilter(SearchFilters.Format.PAPERBACK);

        searchResults = booksSteps.getSearchResultsAsBooks();

        BookAssertions.verifyMultipleBooks(searchResults);
        BookAssertions.verifyContainsTitle(searchResults, bookTitle);
        BookAssertions.verifyFilterApplied(booksSteps, SearchFilters.Format.PAPERBACK.getLabel());
        BookAssertions.verifyAllPaperback(searchResults);
    }
}