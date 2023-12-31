package chat.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Класс для хранения информации о клиентах
 */

public class ClientManager implements Runnable{ // Делаем для запуска отдельным потоком
    private String name;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    /**
     * Коллекцию делаем статической, чтобы избежать зацикливания и мы в будущем, при подключении
     * нового клиента, можем добавлять этого клиента в коллекцию, коллекция будет одна для всех клиентов.
     */
    public static ArrayList<ClientManager> clients = new ArrayList<>();

    public ClientManager(Socket socket) {
        try {
            this.socket = socket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine(); // При подключении, первое сообщение придет с именем подключившегося клиент
            clients.add(this);
            System.out.println(name + " подключился к чату.");
            // Так же сообщаем всем о подключении нового клиента к чату
            broadcastMessage("Server: " + name + " подключился к чату.");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader){
        // Удаляем клиента из коллекции
        removeClient();
        // Обнуляем ссылки на потоки сокета
        try {
            /**
             * Делаем для того, чтобы проверить, если что-то успело проинициализироваться, то это закрываем
             */
            // Завершаем работу буфера на чтение данных
            if (bufferedReader != null) bufferedReader.close();
            // Завершаем работу буфера для записи данных
            if (bufferedWriter != null) bufferedWriter.close();
            // Закрытие соединения с клиентским сокетом
            if (socket != null) socket.close();
        } catch (IOException e){
            // Если словили ошибку ввода/вывода (подключения), то закрываем все потоки и сокет, удаляем клиента из списка
            closeEverything(socket, bufferedWriter, bufferedReader);
        }
    }
    /**
     * Удаление клиента из коллекции
     */
    private void removeClient(){
        clients.remove(this);
        System.out.println(name + " покинул чат.");
    }

    @Override
    public void run() {
        /**
         * При вызове класса в отдельном потоке, автоматически сработает метод run()
         * который будет запускать цикл считывания данных от клиента
         */
        while (socket.isConnected()){
            String messageFromClient;
            try{
                // Чтение данных, получение сообщение от клиента
                messageFromClient = bufferedReader.readLine();
                // Как только получили сообщение, сразу отправляем его всем слушателям
                broadcastMessage(messageFromClient);
            }catch (IOException e){
                closeEverything(socket,bufferedWriter, bufferedReader);
                break;
            }
        }
    }
    /**
     * Отправка сообщений всем слушателям
     * ToDo: реализовать приватные сообщения
     */
    private void broadcastMessage(String message) {
        for (ClientManager client : clients) {
            try {
                // Если клиент не равен по наименованию клиенту-отправителю,
                // отправим сообщение
                if (!client.name.equals(name)){
                    client.bufferedWriter.write(message);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            } catch (IOException e){
                closeEverything(socket, bufferedWriter, bufferedReader);
            }
        }
    }

}
