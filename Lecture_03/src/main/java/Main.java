import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String str = "Всем привет!!!";
        // Создаем поток для записи в файл
        FileOutputStream fileOutputStream = new FileOutputStream("file.txt"); // указываем имя созданного файла
        // Создаем поток для записи объекта для нашего файла
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream); // указываем объект
        objectOutputStream.writeObject(str); // производим саму запись
        objectOutputStream.close(); // закрываем поток
    }
}
