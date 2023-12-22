package Seminar.Task_01;
/**
 Задача 1
 * ========
 * <p>
 * Используя SQL, создайте таблицу students с полями id (ключ), name, и age.
 * Реализация подключения к базе данных через JDBC:
 * Напишите Java-код для подключения к базе данных (например, MySQL или PostgreSQL).
 * Реализуйте вставку, чтение, обновление и удаление данных в таблице Students
 * с использованием провайдера JDBC.
 */

import Seminar.Models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Program {
    private final static Random random = new Random();
    public static void main(String[] args) {
        // Указываем адрес для подключения к базе данных
        String url = "jdbc:mysql://localhost:3306/";
        // Указываем пользователя и пароль
        String user = "root";
        String password = "password";
        try {
            // Подключение к базе данных
            Connection connection = DriverManager.getConnection(url, user, password);
            // Создание базы данных
            createDatabase(connection);
            System.out.println("Database created successfully");
            // Использование базы данных
            useDatabase(connection);
            System.out.println("Use database successfully");
            // Создание таблицы
            createTable(connection);
            System.out.println("Create table successfully");
            // Удаление всех данных
//            deleteDataAll(connection, 0);
//            System.out.println("is empty");
            // Добавление данных
            int count = random.nextInt(5, 11);
            for (int i = 0; i < count; i++) {
                insertData(connection, Student.create());
            }
            System.out.println("Insert data successfully");
            // Удаление части данных
//            deleteData(connection, 138);
//            System.out.println("DeleteData successfully");
            //Чтение данных
            Collection<Student> students = readData(connection);
            for (var student : students) {
                System.out.println(student);
            }
            System.out.println("read data successfully");
            // Изменение данных
            for (var student : students) {
                student.updateAge();
                student.updateName();
                updateData(connection, student);
            }
            System.out.println("update successfully");
            // Повторно считываем
            students = readData(connection);
            for (var student : students) {
                System.out.println(student);
            }
            System.out.println("read data successfully");
            //Удаление данных
            for (var student : students) {
                deleteData(connection, student.getId());
            }
            System.out.println("DeleteDate successfully");
            // Повторно считываем
            students = readData(connection);
            for (var student : students) {
                System.out.println(student);
            }
            System.out.println("read data successfully");
            // Закрываем приложение
            connection.close();
            System.out.println("Database connection close successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //region  Вспомогательные методы
    /**
     * Метод создания конкретной базы данных
     */
    private static void createDatabase(Connection connection) throws SQLException {
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS studentsDB;";
        try (PreparedStatement statement = connection.prepareStatement(createDatabaseSQL)){
            statement.execute();
        }
    }
    /**
     * Указать ту базу данных, которую собираюсь использовать
     */
    private static void useDatabase(Connection connection) throws SQLException{
        String userDatabaseSQL = "USE studentsDB;";
        try (PreparedStatement statement = connection.prepareStatement(userDatabaseSQL)){
            statement.execute();
        }
    }
    /**
     * Создание таблицы
     */
    private static void createTable(Connection connection) throws SQLException{
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45), age INT);";
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)){
            statement.execute();
        }
    }
    /**
     * Добавление записи в таблицу student
     */
    private static void insertData(Connection connection, Student student) throws SQLException{
        String insertDataSQL = "INSERT INTO students (name, age) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(insertDataSQL)){
            // в рамках первого ? передаем параметр student.getName() - соответствует индексу
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.executeUpdate();
        }
    }
    /**
     * Метод чтения данных
     */
    private static  Collection<Student> readData(Connection connection) throws SQLException{
        ArrayList<Student> studentsList = new ArrayList<>();
        String readDataSQL = "SELECT * FROM students;";
        try (PreparedStatement statement = connection.prepareStatement(readDataSQL)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                studentsList.add(new Student(id, name, age));
            }
            return studentsList;
        }
    }
    /**
     * Удаление данных из базы данных
     */
    private static void deleteData(Connection connection , int id) throws SQLException{
        String deleteDataSQL = "DELETE FROM students WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(deleteDataSQL)){
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
    /**
     * Test Delete All
     */
    private static void deleteDataAll(Connection connection , int id) throws SQLException{
        String deleteDataSQL = "DELETE FROM students WHERE id>?;";
        try (PreparedStatement statement = connection.prepareStatement(deleteDataSQL)){
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
    /**
     * Метод обновления данных
     */
    private static void updateData(Connection connection, Student student) throws SQLException{
        String updateDataSQL = "UPDATE students SET name=?, age=? WHERE id=?;";
        try(PreparedStatement statement = connection.prepareStatement(updateDataSQL)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setInt(3, student.getId());
            statement.executeUpdate();
        }
    }
    //endregion
}
