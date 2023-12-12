package Task_01;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();
        repository.addBook(new Book("Война и мир", "Лев Толстой", 1985));
        repository.addBook(new Book("Война и мир", "Лев Толстой", 1985));
        repository.addBook(new Book("Капитанская дочка", "Александр Пушкин", 1990));
        repository.addBook(new Book("Сборник поэм", "Сергей Есенин", 1995));
        repository.addBook(new Book("Сборник поэм", "Сергей Есенин", 1995));
        repository.addBook(new Book("Мастер и Маргарита", "Михаил Булгаков", 1992));
        repository.addBook(new Book("Анна Каренина", "Лев Толстой", 1992));
        repository.getBookList().stream().filter(book -> book.author.equals("Лев Толстой")).map(book -> book.name).forEach(System.out::println);
        System.out.println();
        repository.getBookList().stream().filter(book -> book.getYear() > 1990).forEach(System.out::println);
        System.out.println();
        repository.getBookList().stream()
                .map(book -> book.name)
                .distinct().forEach(System.out::println);
    }
}
