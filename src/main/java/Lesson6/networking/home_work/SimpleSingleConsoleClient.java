package Lesson6.networking.home_work;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class SimpleSingleConsoleClient {

    private static final String HOST = "127.0.0.1"; //локальный
    //у сервера есть порт
    private static final int PORT = 8189;
    //private Socket socket;
    private DataInputStream in;//наши потоки данных
    private DataOutputStream out;

    private DataInputStream in2;//наши потоки данных
    private DataOutputStream out2;



    //в отдельном потоке мы будем обрабатывать консоль сервера
    private Thread clientConsoleThread;


    public static void main(String[] args) {
        //создание сервера
        new SimpleSingleConsoleClient().start();

    }


    //ServerSocket слушает определенный порт, образует соединение
    //Socket - еденица соединения есть потоки ввода вывода, клиентская часть соединения
    //1 порт 1 процесс
    public void start() {
        try (var socket = new Socket(HOST, PORT) ) { //слушаем порт
            System.out.println("Connected to server");


            in = new DataInputStream(socket.getInputStream());//инициализируем потоки данных предназначенные на одного клиента
            out = new DataOutputStream(socket.getOutputStream());//потоки готовы

            in2 = new DataInputStream(socket.getInputStream());//инициализируем потоки данных предназначенные на одного клиента
            out2 = new DataOutputStream(socket.getOutputStream());//потоки готовы

            //запуск потока читающего консоль сервера
            startConsoleThread();

            //бесконечно читаем входящий поток
            while (true) {
                var message = in.readUTF();// in.readUTF() - читает строку/сообщение

                System.out.println("Получено сообщение1: " + message);


            }

        }catch (SocketException e){//аккуратная остановка при ловле SocketException
            System.out.println("Connection to server hs been lost");
        }

        catch(IOException e)
         {e.printStackTrace();}
        finally{
        }
        { try {
            shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        try (var socket2 = new Socket(HOST, PORT) ) { //слушаем порт
            System.out.println("Connected to server");


            in2 = new DataInputStream(socket2.getInputStream());//инициализируем потоки данных предназначенные на одного клиента
            out2 = new DataOutputStream(socket2.getOutputStream());//потоки готовы

            //запуск потока читающего консоль сервера
            startConsoleThread();

            //бесконечно читаем входящий поток
            while (true) {

                var message2 = in2.readUTF();

                System.out.println("Получено сообщение2: " + message2);

            }

        }catch (SocketException e){//аккуратная остановка при ловле SocketException
            System.out.println("Connection to server hs been lost");
        }

        catch(IOException e)
        {e.printStackTrace();}
        finally{
        }
        { try {
            shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }


}

    private void shutdown() throws IOException{
        if (clientConsoleThread.isAlive()){
           clientConsoleThread.interrupt();
        }
            System.out.println("Client stopped");


        }


    private void startConsoleThread() {
        clientConsoleThread = new Thread(()->{
            try (var reader = new BufferedReader(new InputStreamReader(System.in)))//читаем консоль
            {
                System.out.print("Enter message for server >>>>>>\n");//пишет сообщение для сервера
                while (!Thread.currentThread().isInterrupted()) //пока поток не прерван
                {if (reader.ready()) { //если что то в reader есть то
                    var clientMessage = reader.readLine();
                    if (clientMessage.equalsIgnoreCase("/quit")){//если клиент вводит quit то.."
                        out.writeUTF("/end");
                        shutdown();}
                    out.writeUTF(clientMessage);}} //обрабатываем

            }catch (IOException e){
                e.printStackTrace();//ловим IOException
            }
           //
            //
        });
        clientConsoleThread.start();


    }


   }
