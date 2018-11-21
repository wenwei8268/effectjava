package com.wenwei.threadPoolExecutors;
/**
 * Created by wenweizww on 2018/9/27.
 */


import org.junit.Test;

import java.util.concurrent.*;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/9/27
 * description:
 */
public class FututerTaskTest {
        public static void main(String[] args) {
            //第一种方式
            ExecutorService executor = Executors.newCachedThreadPool();
            Task task = new Task();
            FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
            executor.submit(futureTask);
            executor.shutdown();

            //第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
        /*Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.start();*/

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            System.out.println("主线程在执行任务");

            try {
                System.out.println("task运行结果"+futureTask.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            System.out.println("所有任务执行完毕");
        }
        @Test
    public void testFuturePromise(){
//            Promise
        }
    //测试semaphore ,信号量，可以多个线程同时访问一个对象，
        @Test
    public void testSemaphore() throws Exception{
        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
//        ExecutorService exec = Executors.newFixedThreadPool(20);
        // 只能5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        // 模拟20个客户端访问
        for (int index = 0; index < 50; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 获取许可
                        System.out.println("begin run the runnable " + NO);
                        semp.acquire();
                        System.out.println("Accessing: " + NO);
                        Thread.sleep((long) (Math.random() * 6000));
                        // 访问完后，释放
                        semp.release();
//                        System.out.println("-----------------" + semp.availablePermits());
//                        availablePermits()指的是当前信号灯库中有多少个可以被使用
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        System.out.println("-----------------" + semp.availablePermits());

                    }
                }
            };
            exec.execute(run);
        }
        // 退出线程池
//            Thread.sleep(60000);
//            exec.shutdown();
            exec.awaitTermination(1000,TimeUnit.MILLISECONDS);
//            exec.shutdownNow();
        }

}
    class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程在进行计算");
            Thread.sleep(3000);
            int sum = 0;
            for(int i=0;i<100;i++)
                sum += i;
            return sum;
        }
}
