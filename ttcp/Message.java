package ttcp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Message {
    private Socket client;
    Scanner input = new Scanner(System.in);

    public Message(Socket client){
        this.client = client;
    }

    public synchronized void sentMessage(){
        try{
            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
            PrintStream out = new PrintStream(client.getOutputStream());
            System.out.println("我： ");
            String str = buff.readLine();
            if(str == "")
                Thread.sleep(10000);
            out.println(str);
        }
        catch (Exception e){
                e.printStackTrace();
            }
        }

    public synchronized void getMesssage(){
            try{
                BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String str = buf.readLine();
                System.out.println("对方： " + str);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
