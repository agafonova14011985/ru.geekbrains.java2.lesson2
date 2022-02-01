package Lesson6.networking.two;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSingleConsoleServer {

    //у сервера есть порт
    private static final int PORT = 8189;
    private Socket socket;
    private DataInputStream in ;//наши потоки данных
    private DataOutputStream out;
    //в отдельном потоке мы будем обрабатывать консоль сервера
    private Thread serverConsoleThread;

    public static void main(String[] args) {
        //создание сервера
       // new SimpleSingleConsoleClient().start();
        new SimpleSingleConsoleServer().start();
    }

    //ServerSocket слушает определенный порт, образует соединение
    //Socket - еденица соединения есть потоки ввода вывода, клиентская часть соединения
    //1 порт 1 процесс
    public void start() {
        try (var serverSocket = new ServerSocket(PORT)){ //слушаем порт
            System.out.println("serve started");
            waitForConnection(serverSocket);

            //запуск потока читающего консоль сервера
            startConsoleThread();

            //бесконечно читаем входящий поток
            while (true){
                var message = in.readUTF();   // in.readUTF() - читает строку/сообщение
                //отключение
                if (message.startsWith("/end")){
                    shutdown();
                    break;
                }
                System.out.println("Получено сообщение: " + message);
                //отправка ответа:
                //out.writeUTF("Эхо: " + message);
            }
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                shutdown();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void shutdown() throws IOException{
        if (serverConsoleThread.isAlive()){
            serverConsoleThread.interrupt();
        }
        if (socket != null){
            socket.close();
            System.out.println("Server stopped");
        }
    }

    private void startConsoleThread() {
        serverConsoleThread = new Thread(()->{
            try (var reader = new BufferedReader(new InputStreamReader(System.in)))//читаем консоль
            {
                System.out.print("Enter message for client >>>>>>\n");
                while (!Thread.currentThread().isInterrupted()) //пока поток не прерван
                {if (reader.ready()) { //если что то в reader есть то
                    var serverMessage = reader.readLine();
                    out.writeUTF(serverMessage);}} //обрабатываем

            }catch (IOException e){
                e.printStackTrace();//ловим IOException
            }
        });
        serverConsoleThread.start();
    }

    private void waitForConnection(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting for connection...\n/ Ждем подключения...");
        //соединение получаем из serverSocket с помощью .accept() - в этот момент
        // сервер начинает слушать порт который мы указали и поток не двигается дальше,
        // пока никто не подключится и не получится соединение socket
        var socket = serverSocket.accept();
        //клиент подключился, образовался socket
        System.out.println("Client connected");//клиент подключился

        //когда есть соединение socket, далее
        //работа с потоками данный  абстрактный класс DataInputStream - поток ввода
        //DataOutputStream - поток вывода
        in = new DataInputStream(socket.getInputStream());//инициализируем потоки данных предназначенные на одного клиента
        out = new DataOutputStream(socket.getOutputStream());//потоки готовы
    }


}
