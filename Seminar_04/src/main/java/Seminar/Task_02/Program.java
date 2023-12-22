package Seminar.Task_02;

import Seminar.Models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryDelegatingImpl;

/**
 * Задача 2
 * ========
 * <p>
 * Настройте Hibernate, связав его с вашей базой данных.
 * Создайте класс Student в Java, аннотируя его как сущность Hibernate.
 * Используя Hibernate, реализуйте вставку, чтение, обновление и
 * удаление данных в таблице students.
 * Обратите внимание на использование сессий и транзакций в Hibernate.
 */
public class Program {
    public static void main(String[] args) {
        /**
         * Создаем фабрику сессий для подключения к БД
         */
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml") // Указываем файл конфигурации
                .addAnnotatedClass(Student.class) // Добавляем аннотированный файл
                .buildSessionFactory(); // Строим фабрику сессий
        /**
         * Создаем сессию с базой данных
         */
        Session session = sessionFactory.getCurrentSession();
        try {
            // Начинаем транзакцию
            session.beginTransaction();

            // Создаем объект студента для теста
            Student student = Student.create();
            System.out.println(student);

            // Сохранение объекта в БД
            session.save(student);
            System.out.println("Object student save successfully");
            System.out.println(student);

            // Чтение объекта из БД
            Student retrievedStudent = session.get(Student.class, student.getId());
            System.out.println("Object student retrieved successfully");
            System.out.println("Retrieved student object: " + retrievedStudent);

            // Обновление объект
            retrievedStudent.updateName();
            retrievedStudent.updateAge();
            session.update(retrievedStudent);
            System.out.println("Object student update successfully");
            System.out.println(retrievedStudent);
            // Удаление объекта
            session.delete(retrievedStudent);
            System.out.println("Object student delete successfully");
            System.out.println(retrievedStudent);
            // Далее завершаем транзакцию, сохраняем все изменения с помощью коммита
            session.getTransaction().commit();
            System.out.println("Transaction commit successfully");
        } finally {
            // Закрываем сессию
            session.close();
        }

    }
}
