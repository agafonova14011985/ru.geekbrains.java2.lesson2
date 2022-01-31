package Lesson6.networking.one;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

public class SimpleEchoServer {

    //сделаем простой эхосервер, который получает сообщения и обратно их возвращает

    private static final int PORT = 8189;

    //ServerSocket слушает определенный порт, образует соединение
    //Socket - еденица соединения есть потоки ввода вывода, клиентская часть соединения
    //1 порт 1 процесс
    public static void main(String[] args) {
        try (var serverSocket = new ServerSocket(PORT)){
            System.out.println("serve started");
            //соединение получаем из serverSocket с помощью .accept() - в этот момент
            // сервер начинает слушать порт который мы указали и поток не двигается дальше,
            // пока никто не подключится и не получится соединение socket
            var socket = serverSocket.accept();
            //клиент подключился, образовался socket
            System.out.println("Client connected");

            //когда есть соединение socket, далее
            //работа с потоками данный  абстрактный класс DataInputStream - поток ввода
            //DataOutputStream - поток вывода
            var in = new DataInputStream(socket.getInputStream());
            var out = new DataOutputStream(socket.getOutputStream());//потоки готовы

            //бесконечно читаем входящий поток и отправляем эхо в исходящий
            while (true){
                var message = in.readUTF();   // in.readUTF() - читает строку
                System.out.println("Получено сообщение: " + message);
                //отправка ответа:
                out.writeUTF("Эхо: " + message);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }



   /*public static void main(String[] args) {

        //сколько процессоров в данной системе доступны джаве и
        // от этого отталкиваться и на столько потоков-параллель
        //System.out.println(Runtime.getRuntime().availableProcessors());

        //среда выполнения Runtime, с помощью Runtime.getRuntime().exec() можно запустить другой процесс,
        // все что угодно где есть, что-то, на хосте где она запускается
       //Runtime.getRuntime().exec();

        //ip протокол передачи данных по сети/версия 4 и версия 6/
        //адреса 4. закончились в 2000 году/
        //у каждой машины в сети есть свой адрес
        //при подключении к другому пк, мф подключаемся к адресу и порту
        //при вводе url- в dns сервере по запросу получаеем ip адрес... и порты
        //что бы сделать приложение и при запуске оно говорит ОС,
        // что данное приложение слушает данный порт
        //если есть подключение то выполняется какая то логика
        //эти подключения называют сокетами


    }*/

}
