import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner numere = new Scanner(System.in);
        System.out.print("Introduceti primul numar: ");
        int nr1 = numere.nextInt();

        System.out.print("Introduceti al doilea numar: ");
        int nr2 = numere.nextInt();

        System.out.println("Numerele adaugate sunt: " + nr1 + " si " + nr2);
        String rezultat = nr1 + "+" + nr2;
        String line = null;
        try(Socket socket = new Socket("localhost", 1080);
            BufferedReader bis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream())) {
                bos.write((rezultat).getBytes());
                bos.flush();

            line = bis.readLine();
            System.out.println(line);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
        }
    }
}