package com.wenwei.threadPoolExecutors;
/**
 * Created by wenweizww on 2018/9/17.
 */


import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
import org.junit.Test;
//import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/9/17
 * description:
 */
public class ThreadPoolTest {
    static AtomicInteger atomicInteger = new AtomicInteger(1);

    private static volatile Integer integer = 1;

    public synchronized static Integer incrementInt(Integer i) {
//        i = i +1;
        return ++i;
    }
    @Test
    public synchronized void testThreadPool() throws InterruptedException {
        long tt = System.currentTimeMillis();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int k = 0; k < 10; k++) {

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        integer = atomicInteger.incrementAndGet();
                        incrementInt(integer);
                        countDownLatch.countDown();
                    } catch (java.lang.Exception e) {
                        System.out.println(e);
                    }


                }
            });
        }
        executorService.shutdown();
//        countDownLatch.wait();
        System.out.println("integer ~~~~" + integer);
        System.out.println("atmoic : " + atomicInteger +"**"+countDownLatch.getCount());

        System.out.println("time cost: "+ (System.currentTimeMillis()-tt));
    }

//    public static void main(String[] args) {
//        long tt = System.currentTimeMillis();
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        for (int k = 0; k < 10; k++) {
//
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        integer = atomicInteger.incrementAndGet();
//                        incrementInt(integer);
//                    } catch (java.lang.Exception e) {
//                        System.out.println(e);
//                    }
//
//
//                }
//            });
//        }
//        executorService.shutdown();
//        System.out.println("integer ~~~~" + integer);
//        System.out.println("atmoic : " + atomicInteger);
//        System.out.println("time cost: "+ (System.currentTimeMillis()-tt));
//    }
//
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);

        long tt = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int k = 0; k < 10; k++) {

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("test");
                        atomicInteger.incrementAndGet();
                    } catch (java.lang.Exception e) {
                        System.out.println(e);
                    }


                }
            });
        }
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
            //    SecurityManager securityManager = System.getSecurityManager();
             //   System.out.println(securityManager.toString());
                System.out.println("executor is runing ");
            }
        }));
        executorService.shutdown();
        System.out.println("atmoic : " + atomicInteger);
        System.out.println("time cost: "+ (System.currentTimeMillis()-tt));
    }


}
