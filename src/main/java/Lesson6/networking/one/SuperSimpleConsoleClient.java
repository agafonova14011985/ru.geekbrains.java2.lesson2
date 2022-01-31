package Lesson6.networking.one;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SuperSimpleConsoleClient {

    //HOST - это айпи
    private static final String HOST = "127.0.0.1"; //локальный
    private static final int PORT = 8189;

    //что бы подключиться к серверу мы должны знать где он находится и его порт
    public static void main(String[] args) {
        try (var socket = new Socket(HOST,PORT);
             var scanner = new Scanner(System.in)){
             System.out.println("Подключились к серверу/ Connected to server");

           var in = new DataInputStream(socket.getInputStream());
           var out = new DataOutputStream(socket.getOutputStream());//потоки готовы

            //бесконечно читаем входящий поток и отправляем эхо в исходящий
            while (true){
                var message = scanner.nextLine();//берем сообщение из сканера
                out.writeUTF(message);//отправляем сообщение на сервер

                Thread.sleep(100);//что бы не тупило

                //получить сообщение с сервера
                message = in.readUTF();
                System.out.println("Получено /Received: " + message);
            }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
