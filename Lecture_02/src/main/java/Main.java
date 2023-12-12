import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        Car car = new Car(); // Не можем создать объект класса с пустым конструктором, так он переопределен
        /**
         * Можем обмануть систему и создать объект не вызывая конструктор обычным способом:
         * Класс создаем параметризированным, так как не знаем какой будет класс
         * Статический метод forName позволяет достать класс по имени (информацию о классе)
         */
        Class<?> car = Class.forName("Car");
        // на случай отсутствия класса добавляем throws ClassNotFoundException
        /**
         * Получаем информацию о наших конструкторах, так же создаем параметризированный конструктор
         * Так как конструкторов может быть несколько, обязательно создаем массив
         */
        Constructor<?>[] constructors = car.getConstructors();
        System.out.println(constructors); // [Ljava.lang.reflect.Constructor;@7b23ec81 - нашли 1 конструктор
        /**
         * Создаем самый главный объект класса Object, и конструктором вытащим первый элемент массива
         * newInstance - конструктор предоставляет нам функционал для того, чтобы создавать объект этого класса
         * в newInstance нам предлагают передать object'ы для инициализации, а в качестве object'а у нас идет String
         * по этому пишем "range"
         */
        Object range = constructors[0].newInstance("range");
        System.out.println(range); // Car name is: range and max speed = 230
        /**
         * Добавили полей в класс Car и сделали их приватными, в конструкторе есть инициализация.
         * Создаем массив для новых полей класса Car.
         * Далее берем объект, достаем из него класс и из него список всех полей
         */
        Field[] fields = range.getClass().getFields();
        /**
         * Создаем временную переменную int (мы знаем, что последнее поле int) и кладем в нее значение поля
         * Далее в последний элемент массива устанавливаем новое значение (указывая объект и новое значение)
         */
        int tmp = fields[fields.length - 1].getInt(range);
        fields[fields.length - 1].setInt(range, tmp + 50);
        System.out.println(range); // Будет ошибка, так как поле private, меняем... и ошибки нет
        // Car name is: range and max speed = 280
        // Мы не использовали прямых set'еров, поменяли значение переменной
        /**
         * Делаем тоже для методов, создаем get/set для price
         * Выводя методы в консоль, мы увидим не только наши 3 метода, но и методы класса Object.
         * Чтобы получить только свои методы, нужно заменить getMethods() на getDeclaredMethods()
         */
//        Method[] methods = range.getClass().getMethods();
        Method[] methods = range.getClass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i]);
        }
    }
}
