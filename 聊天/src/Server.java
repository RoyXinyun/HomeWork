import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;

/*
 服务器方向，连接各种客户端，接收每个客户端消息，显示消息，并向每个客户端发送消息
 */
class Server {
    ServerSocket server = null;
    Socket client = null;
    static ArrayList<Socket> list = new ArrayList<Socket>();

    public Socket start(){
        try {
                System.out.println("服务器运行，等待客户端连接");
                client = server.accept();//接受客户端连接
                list.add(client);
        } catch (Exception e) {
            System.out.println("服务器连接失败");
        }
        return client;
    }

    public void open(){
        try {
            server = new ServerSocket(8888);
        } catch (Exception e) {
            System.out.println("服务器连接失败");
        }
    }

    public static void main(String[] args) {
        Server s = new Server();
        s.open();
        while(true){
            Socket client = s.start();
            new Thread(new ServerThread(client,list)).start();  //打破线程阻塞状态
        }
    }
}

class ServerThread implements Runnable {
    Socket client = null;
    BufferedReader in = null;
    PrintStream out = null;
    ArrayList<Socket> list = null;
    public ServerThread(Socket client,ArrayList list){
        this.client = client;
        this.list = list;
    }
    public void run() {
        try {
            while(true){
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String getMessage = in.readLine();
                System.out.println(getMessage);
                for(int i = 0;i < list.size();i++) {
                    out = new PrintStream(list.get(i).getOutputStream());
                    out.println(getMessage);
            }
            }
        }
         catch (Exception e) {
            System.out.println("失败");
        }
    }
}
