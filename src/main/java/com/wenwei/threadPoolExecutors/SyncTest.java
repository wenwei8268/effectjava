package com.wenwei.threadPoolExecutors;
/**
 * Created by wenweizww on 2018/10/10.
 */


import java.util.Objects;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/10/10
 * description:
 */
public class SyncTest extends Thread{
    @Override
    public  void run(){
        data();
    }
    public  void data(){
        synchronized (Objects.class) {
            System.out.println("test开始.." + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test结束.." + Thread.currentThread().getId());
        }

    }
}

class A{
    public static void main(String[] args){
      while (true){
            SyncTest syncTest = new SyncTest();
            syncTest.start();
        }
    }

}
