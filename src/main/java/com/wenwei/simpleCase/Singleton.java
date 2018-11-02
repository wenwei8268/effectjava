package com.wenwei.simpleCase;
/**
 * Created by wenweizww on 2018/9/18.
 */


/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/9/18
 * description:
 */
public final class Singleton {
    private volatile static  Singleton singleton ;

    public Singleton() {
    }
    public Singleton getInstance(){
        if(singleton == null){
            synchronized (Singleton.class){
                 singleton = new Singleton();
            }
        }
        return singleton;
    }
}
