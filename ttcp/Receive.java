package ttcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receive implements Runnable {
    private Message mess;
    private boolean flag = true;

    public Receive(Message mess){
        this.mess = mess;
    }

    public void run(){
        while(true){
           mess.getMesssage();
        }
    }
}
