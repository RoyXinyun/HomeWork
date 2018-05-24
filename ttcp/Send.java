package ttcp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Send implements Runnable {
    private Message mess = null;
    private boolean flag = true;
    public Send(Message mess){
        this.mess = mess;
    }
    public void run(){
        for(int i = 0;i < 10;i++)
          mess.sentMessage();
    }
}
