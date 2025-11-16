package com.amz.books.pages.components;

import com.amz.books.model.Book;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchResults {

    private static final String BOOK_TITLE_XPATH = "//div[@data-cy='title-recipe']//h2";
    private static final String BOOK_IS_PAPERBACK_XPATH = "//div[@data-cy='price-recipe']//a[contains(.,'Paperback')]";
    private static final String BOOK_PUBLISH_DATE_XPATH = "//div[@class='a-row']/span[@class='a-size-base a-color-secondary a-text-normal']";

    private final List<WebElement> bookElements;

    public SearchResults(List<WebElement> bookElements) {
        this.bookElements = bookElements;
    }

    public int getCount() {
        return bookElements.size();
    }

    public List<Book> toBookList() {
        List<Book> books = new ArrayList<>();
        for (WebElement bookElement : bookElements) {
            String title = getTextOrDefault(bookElement, BOOK_TITLE_XPATH);
            String publishDate = getTextOrDefault(bookElement, BOOK_PUBLISH_DATE_XPATH);
            boolean isPaperback = !bookElement.findElements(By.xpath(BOOK_IS_PAPERBACK_XPATH)).isEmpty();
            books.add(new Book(title, publishDate, isPaperback));
        }
        return books;
    }

    private String getTextOrDefault(WebElement parent, String xpath) {
        try {
            return parent.findElement(By.xpath(xpath)).getText().trim();
        } catch (NoSuchElementException e) {
            return "N/A";
        }
    }
}