package com.wenwei.threadPoolExecutors;
/**
 * Created by wenweizww on 2018/9/27.
 */


import java.util.concurrent.*;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/9/27
 * description:
 */
public class FutureTaskTest_ {
        public static void main(String[] args) {
            ExecutorService executor = Executors.newCachedThreadPool();
            Task1 task = new Task1();
            Future<Integer> result = executor.submit(task);
            executor.shutdown();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            System.out.println("主线程在执行任务");
            while(!result.isDone()){
                System.out.println("12");

            }
            try {
                System.out.println("task运行结果"+result.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

//            try {
//                System.out.println("task运行结果"+result.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }

            System.out.println("所有任务执行完毕");
        }
    }
    class Task1 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程在进行计算");
            Thread.sleep(3000);
            int sum = 0;
            for(int i=0;i<10000;i++)
                sum += i;
            return sum;
        }
}
