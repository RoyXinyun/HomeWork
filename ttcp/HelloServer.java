package ttcp;
import java.net.*;

public class HelloServer {
    public static void main(String[] args) throws Exception{
        ServerSocket server = null;
        Socket user = null;

        server = new ServerSocket(8888);

        System.out.println("服务器运行，等待客户端连接 ");
        user = server.accept();

            Message message = new Message(user);
            new Thread(new Receive(message)).start();


    }
}
