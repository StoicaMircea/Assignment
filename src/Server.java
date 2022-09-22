import java.io.*;
import java.net.*;
import java.nio.*;

import static java.lang.Integer.sum;

public class Server {
    public static void main(String[] args) {
        System.out.println("Programul ruleaza!");
        try(ServerSocket serverSocket = new ServerSocket(1080);
            Socket client = serverSocket.accept();
            BufferedReader bis = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedOutputStream bos = new BufferedOutputStream(client.getOutputStream())) {

            System.out.println("Clientul s-a conectat!");
            System.out.println("Mesajul de la client: ");

            String[] array = null;
            String line = bis.readLine();
            while(line != null && !line.equals("")){
                array = line.split("\\+", 2);
                System.out.println(Integer.sum(Integer.parseInt(array[0]), Integer.parseInt(array[1])));
                bos.write(Integer.sum(Integer.parseInt(array[0]),Integer.parseInt(array[1])));
                bos.flush();
                line = bis.readLine();
            }
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}