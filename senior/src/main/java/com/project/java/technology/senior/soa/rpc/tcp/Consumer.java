package com.project.java.technology.senior.soa.rpc.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author yin
 * @desc Consumer
 * @date 2018/12/19 22:44
 */
public class Consumer {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException {
        //接口名
        String interfaceName = "com.project.java.technology.senior.soa.rpc.tcp.service.ISayHelloService";
        //方法名
        String methodName = "sayHello";
        //参数类型
        Class<?> [] parameterTypes = {String.class};
        //参数
        Object [] parameters = {"Tom"};

        //通过socket远程调用
        Socket socket = new Socket("127.0.0.1", 1234);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeUTF(interfaceName);
        outputStream.writeUTF(methodName);
        outputStream.writeObject(parameterTypes);
        outputStream.writeObject(parameters);

        //读取返回参数
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        Object result = inputStream.readObject();
        inputStream.close();
        outputStream.close();
        socket.close();
        System.out.println(result);
    }
}
