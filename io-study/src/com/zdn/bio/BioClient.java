package com.zdn.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class BioClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            while (true) {
                try {
                    socket.getOutputStream().write((new Date() + ": hello bio").getBytes());
                    socket.getOutputStream().flush();
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            System.out.println("client io exception");
        }
    }
}
