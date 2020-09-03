package com.project.java.technology.senior.soa.rpc.tcp;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.project.java.technology.core.model.User;

import java.io.*;

/**
 * @author yin
 * @desc SerializeObject
 * @date 2018/12/19 22:03
 */
public class SerializeObject {

    /**
     * 将对象输出为字节数组，再将字节数组转为对象
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //基于java内置的序列化与反序列化方式
        User user = new User();
        user.setName("张三");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(user);
        byte [] bytes = byteArrayOutputStream.toByteArray();

        //反序列化
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        User person1 = (User) objectInputStream.readObject();
        System.out.println(person1);



        //基于hessian方式的序列化与反序列化方式
        ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
        HessianOutput hessianOutput = new HessianOutput(byteArrayOutputStream1);
        hessianOutput.writeObject(user);
        byte [] bytes1 = byteArrayOutputStream1.toByteArray();

        //反序列化
        ByteArrayInputStream byteArrayInputStream1 = new ByteArrayInputStream(bytes1);
        HessianInput hessianInput = new HessianInput(byteArrayInputStream1);
        User person2 = (User) hessianInput.readObject();
        System.out.println(person2);
    }
}
