package com.wenwei.serialization.defaultJdk;
/**
 * Created by wenweizww on 2018/11/2.
 *
 * 代码中先将对象转化成object对象
 *
 */


import com.oracle.tools.packager.IOUtils;
import com.wenwei.FileUtils;

import java.io.*;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/11/2
 * description:
 */
public class SerializableDemo {
    public static void main(String[] args) {
        //Initializes The Object
        User user = new User();
        user.setJvmId("hollis");
//        user.setNumber("male");
        user.setNumber(23);
//        user.setBirthday(new Date());
        System.out.println(user);

        //Write Obj to File
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            IOUtils.closeQuietly(oos);
        }

        //Read Obj from File
        File file = new File("tempFile");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            User newUser = (User) ois.readObject();
            System.out.println(newUser);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
//            IOUtils.closeQuietly(ois);
//            try {
////                FileUtils.forceDelete(file);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

    }
}
