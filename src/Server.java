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
            int sum = 0;
            while(line != null && !line.equals("")){
                array = line.split("\\+", 2);
                sum = Integer.sum(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
                System.out.println("Am primit " + sum);
                System.out.println("sending sum to client");
                bos.write(String.valueOf(sum).getBytes());
                bos.flush();
                bos.write(String.valueOf("\n").getBytes());
                bos.flush();
                line = bis.readLine();
            }
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}