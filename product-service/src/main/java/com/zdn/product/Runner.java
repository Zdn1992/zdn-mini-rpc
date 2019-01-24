package com.zdn.product;

import com.zdn.product.api.IProductService;
import com.zdn.product.service.ProductServiceImpl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Runner {

    public static void main(String[] args) throws Exception {
        // 开启服务端 设置监听端口8888
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            Socket socket = server.accept();
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            String apiClassName = input.readUTF();
            String methodName = input.readUTF();
            Class[] parameterTypes = (Class[]) input.readObject();
            Object[] apiArgs = (Object[]) input.readObject();

            // 服务的注册
            Class clazz = null;
            if (IProductService.class.getName().equals(apiClassName)) {
                clazz = ProductServiceImpl.class;
            }
            Method method = clazz.getMethod(methodName, parameterTypes);
            IProductService productService = (IProductService) clazz.newInstance();
            Object invoke = method.invoke(productService, apiArgs);

            // 返回客户端
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(invoke);
            output.flush();


            // 关闭资源
            input.close();
            output.close();
            socket.close();
        }
    }
}
