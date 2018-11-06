package com.wenwei.serialization.kryo;
/**
 * Created by wenweizww on 2018/11/2.
 *
 * 代码中先将对象转化成object对象
 *
 */


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.wenwei.serialization.defaultJdk.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/11/2
 * description:
 */
public class SerializableKryoDemo {
    public static void main(String[] args) throws FileNotFoundException {
        //初始化kryo对象
        Kryo kryo = new Kryo();
        // 初始化需要序列化的对象
        List<User> userList = new ArrayList<>();
        for (int i = 0; i <1024000 ; i++) {
            User user = new User();
            user.setJvmId("hello world");
            user.setNumber(i);
            Date date = new Date();
            user.setDate(date);
            userList.add(user);
//            System.out.println(user);
        }

        //将user对象注册到kryo中
        kryo.register(User.class);
        //如果对象中含有时间类型，需要另外注册date https://github.com/EsotericSoftware/kryo/issues/23
        kryo.register(Date.class);
        kryo.register(ArrayList.class);
        //Write Obj to File
        Output output = new Output(1024,-1);
//        output.setOutputStream(new FileOutputStream("temp.bin"));
        kryo.writeObject(output,userList);
        output.close();


       //read  object from file
        Input input = new Input(output.getBuffer(),0,output.position());
        List<User> userList1 = kryo.readObject(input,ArrayList.class);

        List<User> userList2  = kryo.copy(userList1);
        List<User> userList3  = kryo.copyShallow(userList1);

        System.out.println(userList1 == userList2);
//        System.out.println(userList2);
        System.out.println(userList3 == userList1);
        System.out.println(userList3 == userList2);
        input.close();


    }
}
