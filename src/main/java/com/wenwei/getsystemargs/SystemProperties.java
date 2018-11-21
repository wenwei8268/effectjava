package com.wenwei.getsystemargs;
/**
 * Created by wenweizww on 2018/11/17.
 */


import org.junit.*;

import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/11/17
 * description:
 */
public class SystemProperties {
    @org.junit.Test
    public void simpleProcessNum(){
        System.out.println(""+Runtime.getRuntime().availableProcessors());
        System.out.println(Integer.MAX_VALUE);

        Object maybeSelectorImplClass = AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                try {
                    return Class.forName(
                            "sun.nio.ch.SelectorImpl",
                            false,
                            URLClassLoader.getSystemClassLoader()); // 成功，则返回该类
                } catch (Throwable cause) {
                    return cause; // 失败，则返回该异常
                }
            }
        });
        System.out.println(maybeSelectorImplClass.getClass().getClassLoader());

    }
}
