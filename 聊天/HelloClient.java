package ttcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

class ClientThread implements Runnable{
    BufferedReader buff = null;
    Socket client = null;
    InetAddress remAdd;

    public void run(){
        try {
            //输入ip和端口号
            buff = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("IP");
            String ip = buff.readLine();
            System.out.println("port");
            String str = buff.readLine();
            int port = Integer.parseInt(str);
            //建立连接
            client = new Socket(ip,port);

            //启动发送消息线程
            new Thread(new SendThread(client)).start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Thread(new ClientThread()).start();
    }
}

/**
 * 发送消息线程
 */
class SendThread implements Runnable {
    private Socket client;
    public SendThread(Socket client){
        this.client = client;
    }
    public void run(){
        try{
            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
            PrintStream out = new PrintStream(client.getOutputStream());
            while(true){
                System.out.println("我： ");
                String str = buff.readLine();
                if(str == "")
                    Thread.sleep(10000);
                out.println(str);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

