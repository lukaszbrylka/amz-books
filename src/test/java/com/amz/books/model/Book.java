package com.amz.books.model;

public class Book {
    private String title;
    private String publishDate;
    private boolean isPaperback;

    public Book(String title, String publishDate, boolean isPaperback) {
        this.title = title;
        this.publishDate = publishDate;
        this.isPaperback = isPaperback;
    }


    public String getTitle() {
        return title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public boolean isPaperback() {
        return isPaperback;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setPaperback(boolean isPaperback) {
        this.isPaperback = isPaperback;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", isPaperback=" + isPaperback +
                '}';
    }
}