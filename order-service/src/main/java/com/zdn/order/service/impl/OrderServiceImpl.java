package com.zdn.order.service.impl;

import com.zdn.order.service.OrderService;
import com.zdn.product.api.IProductService;
import com.zdn.product.bean.Product;

import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class OrderServiceImpl implements OrderService {

    @Override
    public void createOrder(long productId) {
        // 通过订单controller访问此service ...


        // 远程调用根据产品Id获取产品信息
        IProductService productService = (IProductService) rpc(IProductService.class);
        Product product = productService.getProductById(1);
        System.out.println(product);

        // 获取到产品后,调用生成订单的逻辑 ...
    }

    Object rpc(final Class clazz) {
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
                (proxy, method, args) -> {
                    Socket socket = new Socket("127.0.0.1", 8888);

                    // 获取远程调用的参数信息
                    String className = clazz.getName();
                    String methodName = method.getName();
                    Class[] parameterTypes = method.getParameterTypes();

                    ObjectOutput output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeUTF(className);
                    output.writeUTF(methodName);
                    output.writeObject(parameterTypes);
                    output.writeObject(args);
                    output.flush();

                    ObjectInput input = new ObjectInputStream(socket.getInputStream());
                    Object obj = input.readObject();
                    // 关闭资源
                    output.close();
                    input.close();

                    socket.close();
                    return obj;
                });
    }

}
