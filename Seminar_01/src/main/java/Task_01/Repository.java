package Task_01;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<Book> bookList;

    public Repository() {
        this.bookList = new ArrayList<>();
    }
    public void addBook(Book book) {
        bookList.add(book);
    }
    public Repository(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getBookList() {
        return bookList;
    }
}
