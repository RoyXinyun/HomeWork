package ttcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class HelloClient{
    public static void main(String[] args) throws Exception{
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("IP");
        String ip = buff.readLine();
        System.out.println("port");
        String str = buff.readLine();
        int port = Integer.parseInt(str);

            Socket client = null;
            client = new Socket(ip,port);
            Message message = new Message(client);

            new Thread(new Send(message)).start();

    }

}
