package com.wenwei;
/**
 * Created by wenweizww on 2018/11/17.
 */


import org.junit.*;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/11/17
 * description:
 */
public class systemProperties {
    @org.junit.Test
    public void simpleProcessNum(){
        System.out.println(""+Runtime.getRuntime().availableProcessors());
        System.out.println(Integer.MAX_VALUE);

    }
}
