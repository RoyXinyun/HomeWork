package client2;
import java.io.*;
import java.net.*;

/*
客户端方向，可用于接收服务器的消息并打印到控制台,也可发送消息到服务器
输入端口和ip，连接服务器
 */
class ClientThread implements Runnable {
    Socket client = null;
    PrintStream print = null;
    BufferedReader input = null;

    public void run(){
        ClientThread c = new ClientThread();
        try{
            Socket client1 = c.connection();
            new Thread(new SendThread(client1)).start();
            new Thread(new ClientReceiveThread(client1)).start();
        }catch (Exception e){
            System.out.println("连接失败");
        }

    }
    public Socket connection()throws Exception{
        input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("--------------------欢迎进入聊天室---------------------");
        //连接服务器
        System.out.println("输入ip:");
        String ip = input.readLine();
        System.out.println("输入端口：");
        String port = input.readLine();
        client = new Socket(ip,Integer.parseInt(port));
        return client;
    }

    public static void main(String[] args) throws Exception {
        new Thread(new ClientThread()).start();
    }
}
//初始化输出流，接受服务器消息，输入流，从键盘接收消息
class ClientReceiveThread implements Runnable {
    Socket client = null;
    BufferedReader in = null;
    public ClientReceiveThread(Socket client){
        this.client = client;
    }
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while(true){
                String getMessage = in.readLine();
                System.out.println(getMessage);
            }
        } catch (IOException e) {
            System.out.println("接受失败");
        }
    }
}


class SendThread implements Runnable{
    BufferedReader buff = null;
    PrintStream out = null;
    Socket client = null;
    String name;

    public SendThread(Socket client)throws Exception{
        buff = new BufferedReader(new InputStreamReader(System.in));
        this.client = client;
        System.out.println("请输入您的昵称：");
        name = buff.readLine();
    }
    public void run(){
        try {
            out = new PrintStream(client.getOutputStream());
            while(true){
                System.out.println("输入：");
                String inputMessage = name + ": " + buff.readLine();
                out.println(inputMessage);
            }
        } catch (IOException e) {
            System.out.println("发送失败");
        }
    }
}