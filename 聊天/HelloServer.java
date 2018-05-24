package ttcp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

class HelloServer {
    public static void main(String[] args) throws Exception{
        //建立服务器
        ServerSocket server = null;
        Socket user = null;
        server = new ServerSocket(8888);

        while(true){
            System.out.println("服务器运行，等待客户端连接 ");
            user = server.accept();

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

