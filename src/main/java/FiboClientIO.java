import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class FiboClientIO {
    public static void main(String[] args) {

        // Определяем сокет сервера
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 23444);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Получаем входящий и исходящий потоки информации
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            String msg;
            while (true) {
                System.out.println("Введите искомый номер члена ряда Фибоначчи...");
                msg = scanner.nextLine();
                out.println(msg);
                if ("end".equals(msg)) break;

                System.out.println("SERVER: " + msg + "-й елемент ряда Фибоначи равен : " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
