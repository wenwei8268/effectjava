package com.wenwei.serialization.fastjson;
/**
 * Created by wenweizww on 2018/11/7.
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.wenwei.serialization.defaultJdk.User;

import java.util.Date;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/11/7
 * description:
 */
public class FstSerializableDemo {
    public static void main(String[] args){
        User user = new User();
        user.setJvmId("hollis");
//        user.setNumber("male");
        user.setNumber(23);
        user.setDate(new Date());
        //序列化对象
        String s =JSON.toJSONString(user);
        System.out.println(s);
//        String s = JSON.toJSONString(user);
        //反序列化对象
         user  = JSON.parseObject(s,User.class);

        System.out.println(user.toString());
    }
}
