import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main2 {
    public static void main(String[] args) throws Exception{
        // Создаем поток побайтового чтения из файла
        FileInputStream fileInputStream = new FileInputStream("file.txt");
        // Создаем поток чтения объекта из файла
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        // сохраняем поток в строку предварительно примитивно приводя к строке
        String s = (String) objectInputStream.readObject();
        System.out.println(s);
    }
}
