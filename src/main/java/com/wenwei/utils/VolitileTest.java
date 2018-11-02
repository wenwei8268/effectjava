package com.wenwei.utils;
/**
 * Created by wenweizww on 2018/8/28.
 */


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/8/28
 * description:
 */
public class VolitileTest {
//        public  volatile int inc = 0;
        Lock lock = new ReentrantLock();
    public  AtomicInteger inc = new AtomicInteger();

//        public synchronized void increase() {
//            inc++;
//        }

//    public synchronized void increase() {
//       lock.lock();
//        inc++;
//        lock.unlock();
//    }
    public void increase(){
        inc.getAndIncrement();
    }

        public static void main(String[] args) {
            final VolitileTest test = new VolitileTest();
            for(int i=0;i<10;i++){
                new Thread(){
                    public   void run() {
                        for(int j=0;j<1000;j++)
//                            System.out.println(test.inc);
                            test.increase();
                    };
                }.start();
            }

            while(Thread.activeCount()>1)  //保证前面的线程都执行完
                Thread.yield();
            System.out.println(test.inc);
        }
}
