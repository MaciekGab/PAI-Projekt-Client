package pl.dmcs.model;

import pl.dmcs.enums.BookCategory;

import java.io.Serializable;

public class Book implements Serializable {

    private static final long serialVersionUID = 1;

    private String title;
    private String author;
    private BookCategory category;

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }
}
