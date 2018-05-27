package Chat;
/**
 * 客户端应用，使用线程，可以启发多个线程对象，从而实现多个客户端连接
 *  输入ip和端口连接，可发送消息，发送消息和接受消息在不同的线程中，可以同时发生
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

class ClientThread implements Runnable{
    BufferedReader buff = null;
    Socket client = null;

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
        //启动客户端线程
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
            //初始化输入，输出流，从控制台读取消息，发送到服务器
            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
            PrintStream out = new PrintStream(client.getOutputStream());
            while(true){
                System.out.println("我： ");
                String str = buff.readLine();
                out.println(str);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

