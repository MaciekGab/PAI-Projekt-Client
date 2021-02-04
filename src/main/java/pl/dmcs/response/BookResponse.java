package pl.dmcs.response;

import pl.dmcs.enums.ResponseStatus;
import pl.dmcs.model.Book;
import pl.dmcs.response.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookResponse extends Response implements Serializable {

    private static final long serialVersionUID = 1;

    private List<Book> books = new ArrayList<>();

    public BookResponse(ResponseStatus status, String message, List<Book> books) {
        super(status, message);
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BookResponse{" +
                "books=" + books +
                '}';
    }
}
