package com.amz.books.tests;

import com.amz.books.model.Book;
import com.amz.books.model.enums.SearchFilters;
import com.amz.books.pages.BooksPage;
import com.amz.books.driver.DriverManager;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookSearchTests extends BaseTest {

    private BooksPage booksPage;
    private List<Book> searchResults;
    private String bookTitle;

    @BeforeEach
    void initPage() {
        booksPage = new BooksPage(DriverManager.driver());
    }

    @Test
    @Order(1)
    void shouldOpenBooksPage() {
        String title = DriverManager.driver().getTitle();
        String subpage = "books";
        assertTrue(title.toLowerCase().contains(subpage), "Page title should contain '" + subpage + "'");
        assertEquals(subpage, booksPage.getCurrentSubPageTitle(), "Amazon opened subpage should be '" + subpage + "'");
    }

    @Test
    @Order(2)
    void shouldSearchForThinkingInJava() {
        bookTitle = "thinking in java";
        booksPage.searchForBookByTitle(bookTitle);
        searchResults = booksPage.getSearchResults();

        boolean found = searchResults.stream()
                .map(book -> book.getTitle().toLowerCase())
                .anyMatch(title -> title.contains(bookTitle));

        assertTrue(found, "At least one result should contain '" + bookTitle + "'");
    }

    @Test
    @Order(3)
    void shouldVerifyMultipleSearchResults() {
        int expectedCount = 3;
        long actualCount = searchResults.stream()
                .map(book -> book.getTitle().toLowerCase())
                .filter(title -> title.toLowerCase().contains(bookTitle)).count();
        assertTrue(actualCount >= expectedCount, "Expected at least " + expectedCount + " books with title '" + bookTitle + "', but found " + actualCount);
    }

    @Test
    @Order(4)
    void shouldApplySearchFilter() {
        var filter = SearchFilters.Format.PAPERBACK;
        booksPage.applySingleFilter(filter);
        assertTrue(booksPage.isFilterApplied(filter), filter.getLabel() + " filter should be applied");
    }

    @Test
    @Order(5)
    void shouldVerifyFilteredResults() {
        int expectedFilteredResults = searchResults.size();
        long actualCount = searchResults.stream()
                .filter(Book::isPaperback).count();
        assertEquals(expectedFilteredResults, actualCount, "Filtered results should match 'Paperback'");
    }
}