package com.wenwei.threadPoolExecutors;
/**
 * Created by wenweizww on 2018/9/27.
 */


import org.junit.Test;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/9/27
 * description:
 */
public class LockSupport {
    @Test
    public void test() throws Exception{
        Thread thread = Thread.currentThread();
//        java.util.concurrent.locks.LockSupport.unpark(thread);
        System.out.println(1);
        java.util.concurrent.locks.LockSupport.park(thread);
        System.out.println(12);
        java.util.concurrent.locks.LockSupport.park(thread);
        java.util.concurrent.locks.LockSupport.unpark(thread);
        System.out.println(13);

//        Thread t = new Thread(new Runnable() {
//            private int count = 0;
//
//            @Override
//            public void run() {
//                long start = System.currentTimeMillis();
//                long end = 0;
//                while ((end - start) <= 1000) {
//                    count++;
//                    end = System.currentTimeMillis();
//                }
//                System.out.println("after 1 second.count=" + count);        //等待或许许可
//                java.util.concurrent.locks.LockSupport.park();
//                System.out.println("thread over." + Thread.currentThread().isInterrupted());
//            }
//        });
//        t.start();
//        Thread.sleep(2000);
//        // 中断线程
//        t.interrupt();
//        System.out.println("main over");

    }
}

