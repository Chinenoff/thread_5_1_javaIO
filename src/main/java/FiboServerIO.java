import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FiboServerIO {
    public static void main(String[] args) {
        //  Занимаем порт, определяя серверный сокет
        ServerSocket servSocket = null;
        try {
            servSocket = new ServerSocket(23444);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            //  Ждем одключения клиента и получаем потоки для дальнейшей работы
            try (Socket socket = servSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                String line;
                while ((line = in.readLine()) != null) {
                    int intLine = Integer.parseInt(line);
                    Fiba(intLine);
                    System.out.println(Fiba(intLine));
                    out.println("Echo: " + Fiba(intLine));
                    // Пишем ответ

                    // Выход если от клиента получили end
                    if (line.equals("end")) {
                        break;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public static int Fiba(int number) {
        int fibo1 = 1; // int a = 1, b = 1;
        int fibo2 = 0;
        int fibonachi;
        int result = 0;
        for (int i = 0; i < number; i++) {
            fibonachi = fibo1 + fibo2;
            fibo1 = fibo2;
            fibo2 = fibonachi;
            System.out.print(fibonachi + ", ");
            result = fibonachi;
        }
        return result;
    }

}

