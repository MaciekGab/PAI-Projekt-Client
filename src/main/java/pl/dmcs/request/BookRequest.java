package pl.dmcs.request;

import pl.dmcs.enums.RequestType;

import java.io.Serializable;

public class BookRequest extends Request implements Serializable {

    private static final long serialVersionUID = 1;

    private SearchBy searchBy;
    private String searchPhrase;

    public BookRequest(RequestType requestType, SearchBy searchBy, String searchPhrase) {
        super(requestType);
        this.searchBy = searchBy;
        this.searchPhrase = searchPhrase;
    }

    public SearchBy getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(SearchBy searchBy) {
        this.searchBy = searchBy;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public enum SearchBy {
        title, author, category;
    }
}
