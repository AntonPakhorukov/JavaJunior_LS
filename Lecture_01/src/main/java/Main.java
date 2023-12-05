import com.sun.source.doctree.TextTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Реализация интерфейса
//        PlainInterface plainInterface1 = new PlainInterface() {
//            @Override
//            public String action(int x, int y) {
//                return String.valueOf(x + y);
//            }
//        };
//        // Реализация через лямбда выражение
//        PlainInterface plainInterface2 = (x, y) -> String.valueOf(x + y);
//        // Реализация может быть разной, если потребуется больше, чем одна строка
//        PlainInterface plainInterface3 = (x, y) -> {
//            int a = x*2;
//            int b = y*3;
//            return String.valueOf(a + b);
//        };
//        // Сравнить, какое число больше
//        PlainInterface plainInterface4 = (x, y) -> String.valueOf(Integer.compare(x, y));

//        System.out.println("Решение 1 = " + plainInterface1.action(5, 5));
//        System.out.println("Решение 2 = " + plainInterface2.action(2, 3));
//        System.out.println("Решение 3 = " + plainInterface3.action(4, 9));
//        System.out.println("Решение 4 = " + plainInterface4.action(11, 9));
//        System.out.println("===============");

//        Test test1 = (x, y) -> x + y;
//        Test test12 = Integer::sum;
//        Test test2 = (x, y) -> Integer.compare(x, y);
//        Test test22 = Integer::compare;

//        System.out.println("Test 1 = " + test1.action(5, 20));
//        System.out.println("Test 1.2 = " + test1.action(5, 20));
//        System.out.println("Test 2 = " + test2.action(10, 20));
//        System.out.println("Test 2.2 = " + test2.action(10, 20));
//        System.out.println("===============");
//        List<String> list = Arrays.asList("Привет", "мир", "!", "я", "родился","!!!");
//        System.out.print("Вывод 1: ");
//        printList(list);
//        List<String> list2 = list.stream().filter(str -> str.length() > 4).collect(Collectors.toList());
        // Лист переводим в поток, вызываем метод фильтр и фильтруем строки по длине до 4 символов,
        // так как у нас поток конвейерный, нам нужно вернуть поток в лист, используем метод collect
//        System.out.print("Вывод 2: ");
//        printList(list2);
//        // Вариант полной реализации через stream
//        System.out.print("Вывод 3: ");
//        list.stream().filter(s -> s.length() > 4).forEach(System.out::println);
//        // Stream представляет цепочку настраиваемых команд
//        System.out.print("Вывод 4: ");
//        list.stream().filter(s->s.length() > 4).filter(s -> s.contains("о")).forEach(System.out::println);
//        System.out.println("=========================");
        // Преобразование элементов - map
//        Arrays.asList(1, 2, 3, 4, 5).stream().
//                map(i -> i * 2).
//                forEach(System.out::println);
//        Arrays.asList(1, 2, 3, 4, 5).stream().
//                map(i -> "Число: " + i + " но сейчас это еще  " + i.getClass().getSimpleName()).
//                forEach(System.out::println);
//        Arrays.asList(1, 2, 3, 4, 5).stream().
//                map(i -> "Число: " + i).
//                map(i -> i + " теперь это " + i.getClass().getSimpleName()).
//                forEach(System.out::println);
//        System.out.println("=========================");
        // Сортировка
//        Arrays.asList(9, 5, 7, 5, 1, 3).stream().sorted().forEach(System.out::print);
//        System.out.println();
//        Arrays.asList(9, 5, 7, 5, 1, 3).stream().distinct().forEach(System.out::print);
//        System.out.println();
//        Arrays.asList(9, 5, 7, 5, 1, 3).stream().sorted().distinct().forEach(System.out::print);
//        System.out.println();
//        Arrays.asList(9, 5, 7, 5, 1, 3).stream().distinct().sorted().forEach(System.out::print);
//        System.out.println();
//        // Вывод первого элемента, метод findFirst() находит элемент, метод get() возвращает элемент при наличии
//        System.out.println(Arrays.asList(7, 5, 9, 3).stream().findFirst().get());
//        System.out.println("===========================");
        List<User> list = Arrays.asList(
                new User("Egor", 25),
                new User("Alex", 30),
                new User("Tom", 35));
        list.stream().forEach(System.out::println);
        System.out.println();
        // Изменение параметров объекта
        list.stream().map(user -> new User(user.name, user.age - 5)).forEach(System.out::println);
        System.out.println();
        list.stream().map(user -> new User(user.name, user.age - 5)).filter(user -> user.age < 30).forEach(System.out::println);


    }
    private static void printList(List<String> list){
        for (String item : list) {
            if (!item.equals(list.get(list.size() - 1))) {
                System.out.print(item + " ");
            } else {
                System.out.print(item + "\n");
            }
        }
    }
}
