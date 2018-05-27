package Chat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * 服务器端，可以等待客户端连接，接受所有链接的客户端消息
 */
class HelloServer {
    public static void main(String[] args) throws Exception{
        //建立服务器
        ServerSocket server = null;
        Socket user = null;
        server = new ServerSocket(8888);
        //等待客户端连接
        while(true){
            System.out.println("服务器运行，等待客户端连接 ");
            user = server.accept();
            //启动接受消息线程
            new Thread(new ReceiveThread(user)).start();
        }
    }
}
/**
 * 接收消息线程
 */
class ReceiveThread implements Runnable {
    private Socket client;
    public ReceiveThread(Socket client){
        this.client = client;
    }
    //初始化，用来接受消息输送到控制台
    public void run(){
        try{
            while(true){
                BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String str = buf.readLine();
                System.out.println("somebody： " + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

