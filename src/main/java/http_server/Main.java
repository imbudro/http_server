package http_server;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        try (ServerSocket ss = new ServerSocket(3000)) {
            while (true) {
                Socket s = ss.accept();
                MioThread t = new MioThread(s);
                t.start();
                
            }
        }
    }
}