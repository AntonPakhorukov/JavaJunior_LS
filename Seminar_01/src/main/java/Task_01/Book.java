package Task_01;

public class Book {
    public String name;
    public String author;
    private int year;

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Книга \"" + name + "\", автор \"" + author + "\", год выпуска: " + year;
    }
}
