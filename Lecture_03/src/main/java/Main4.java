import javax.crypto.spec.PSource;
import java.io.*;

public class Main4 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        AnyClass anyClass = new AnyClass("Ivanov", "Ivan", "Ivanovich");
        serialObj(anyClass, "file.txt"); // Чтобы объект можно было сериализовать, он должен
        // имплементировать интерфейс Serializable
        System.out.println(deSerialObj("file.txt"));



    }
    static class AnyClass implements Serializable {
        public String fName;
        public String lName;
        public String patronymic;

        // Для сериализации рекомендуется указывать версию лога
        private static final long serialVersionUID = 1L;
        public AnyClass(String fName, String lName, String patronymic) {
            this.fName = fName;
            this.lName = lName;
            this.patronymic = patronymic;
        }

        @Override
        public String toString() { // Ivanov I I
            return String.format("%s %s.%s.",
                    fName,
                    lName.toUpperCase().charAt(0),
                    patronymic.toUpperCase().charAt(0));
        }
    }
    public static void serialObj(Object o, String file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(o);
        objectOutputStream.close();
    }
    public static Object deSerialObj(String file) throws IOException, ClassNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return objectInputStream.readObject();
    }
}
