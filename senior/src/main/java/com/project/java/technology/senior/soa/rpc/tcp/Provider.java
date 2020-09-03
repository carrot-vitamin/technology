package com.project.java.technology.senior.soa.rpc.tcp;


import com.project.java.technology.senior.soa.rpc.tcp.service.ISayHelloService;
import com.project.java.technology.senior.soa.rpc.tcp.service.impl.SayHelloServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yin
 * @desc Provider
 * @date 2018/12/19 22:28
 */
public class Provider {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(1234);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Map<String, Object> services = new HashMap<>(16);
                services.put(ISayHelloService.class.getName(), new SayHelloServiceImpl());
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();
                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                        //顺序取出接收的数据（注意应该与传入的顺序保持一致）
                        //取得接口名称
                        String interfaceName = inputStream.readUTF();
                        //取得方法名
                        String methodName = inputStream.readUTF();
                        //取得参数类型
                        Class<?> [] parameterTypes = (Class<?>[]) inputStream.readObject();
                        //取得参数
                        Object [] parameters = (Object[]) inputStream.readObject();

                        //取得接口类
                        Class<?> interfaceService = Class.forName(interfaceName);
                        //获取接口实现对象
                        Object serviceImpl = services.get(interfaceName);
                        //取得方法对象
                        Method method = interfaceService.getMethod(methodName, parameterTypes);
                        //调用方法获取返回值
                        Object result = method.invoke(serviceImpl, parameters);

                        //输出接口返回值
                        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                        outputStream.writeObject(result);
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        System.out.println("server start ok");
    }
}
