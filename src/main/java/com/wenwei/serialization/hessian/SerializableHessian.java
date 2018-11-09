package com.wenwei.serialization.hessian;
/**
 * Created by wenweizww on 2018/11/7.
 */


import com.caucho.burlap.io.BurlapOutput;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.wenwei.serialization.defaultJdk.User;

import java.io.*;
import java.util.Date;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/11/7
 * description: hessian for java obeject
 */
public class SerializableHessian {
    public static void main(String[] args) throws IOException {
//        OutputStream os = new FileOutputStream(new File("a.txt"));
//        BurlapOutput out = new BurlapOutput(os);
//        User person=new User();
//        person.setJvmId("12345");
//        person.setNumber(1);
//        person.setDate(new Date());
//
//        out.writeObject(person);
//        out.flush();

//        User person1=new User();
//        person.setJvmId("1235");
//        person.setNumber(112);
//        person.setDate(new Date());
//
//        out.writeObject(person1);
        getSerializable();
        test_dserialization();
    }

    static void getSerializable() throws IOException {
        OutputStream outputStream = new FileOutputStream("a.txt");
        Hessian2Output out = new Hessian2Output(outputStream);
        out.call("nihao", new String[]{"1", "2"});
        //out.writeInt(100);
        //out.writeBoolean(false);
        out.flush();
        out.close();
    }

    //反序列化
    public static void test_dserialization() throws IOException {
        InputStream inputStream = new FileInputStream("a.txt");
        Hessian2Input in = new Hessian2Input(inputStream);
        int ss = in.read();
        in.startCall();
        in.readCall();
        System.out.println((char) ss);
//            in.
//        }
        //System.out.println(in.readInt());
        //System.out.println(in.readBoolean());

    }

}
