package com.zdn.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * bio服务端
 */
public class BioService {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            try {
                // 获取客户端连接
                Socket socket = serverSocket.accept();

                // 每一个新的连接都创建一个线程，负责读取数据
                new Thread(() -> {
                    try {
                        byte[] data = new byte[1024];
                        InputStream inputStream = socket.getInputStream();
                        while (true) {
                            int len;
                            // 按字节流方式读取数据
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println("ThreadName: " + Thread.currentThread().getName()
                                        + " data: " + new String(data, 0, len));
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("server io exception");
                    }
                }).start();

            } catch (IOException e) {
                System.out.println("server io exception");
            }
        }

    }
}
