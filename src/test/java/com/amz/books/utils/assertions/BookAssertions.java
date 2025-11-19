package com.amz.books.utils.assertions;

import com.amz.books.model.Book;
import com.amz.books.steps.BooksPageSteps;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookAssertions {

    public static void verifyMultipleBooks(List<Book> books) {
        assertTrue(books.size() >= 3, "Expected at least 3 books in search results");
    }

    public static void verifyContainsTitle(List<Book> books, String title) {
        boolean found = books.stream()
                .map(book -> book.getTitle().toLowerCase())
                .anyMatch(t -> t.contains(title.toLowerCase()));
        assertTrue(found, "At least one result should contain '" + title + "'");
    }

    public static void verifyFilterApplied(BooksPageSteps steps, String filterLabel) {
        assertTrue(steps.isFilterApplied(filterLabel), filterLabel + " filter should be applied");
    }

    public static void verifyAllPaperback(List<Book> books) {
        long paperbackCount = books.stream().filter(Book::isPaperback).count();
        assertEquals(books.size(), paperbackCount, "All filtered results should be Paperback");
    }
}