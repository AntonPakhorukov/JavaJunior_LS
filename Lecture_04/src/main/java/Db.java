import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
    /**
     * Как правильно создавать адрес:
     * jdbc - это протокол, наш драйвер через который будем подключаться
     * mysql - это наша СУБД
     * localhost - указываем адрес нашего компьютера,
     * в нашем случае это localhost так как запущена на нашем же компьютере
     * 3306 - это стандартный порт для подключения к mysql
     */
    private static final String URL = "jdbc:mysql://localhost:3306";
    // Создаем пользователя
    private static final String USER = "root";
    // Указываем пароль
    private static final String PASSWORD = "NewLinux23";

    public static void con() {
        /**
         * Создаем объект класса Connection, обращаемся к DriverManager
         * и вызываем метод getConnection()
         */
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = con.createStatement();
            statement.execute("DROP SCHEMA testJava");
            statement.execute("CREATE SCHEMA testJava");
            statement.execute("CREATE TABLE testJava.tableJava (id INT NOT NULL, firstname VARCHAR(45) NULL, lastname VARCHAR(45) NULL, PRIMARY KEY(id));");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
