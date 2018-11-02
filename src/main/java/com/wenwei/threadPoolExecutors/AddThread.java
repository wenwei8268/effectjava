package com.wenwei.threadPoolExecutors;
/**
 * Created by wenweizww on 2018/9/18.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.DeflaterOutputStream;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/9/18
 * description:
 */
public class AddThread implements Runnable {
    private CopyOnWriteArrayList<Double> list;

    public AddThread(CopyOnWriteArrayList<Double> list) {
        this.list = list;
    }

    @Override
    public void run(){
        for (int i = 0; i < 20; i++) {
            list.add(Math.random());

        }
    }

}
class ReadThread implements Runnable {
    private CopyOnWriteArrayList<Double> list;

    public ReadThread(CopyOnWriteArrayList<Double> list) {
        this.list = list;
    }

    @Override
    public void run(){
        for (Double d :list) {
            System.out.println(d);

        }
    }

}
 class test{
        public static void main(String[] args) {
            CopyOnWriteArrayList<Double> list = new CopyOnWriteArrayList<>();
            ExecutorService es = Executors.newFixedThreadPool(2);
            es.execute(new AddThread(list));
            es.execute(new AddThread(list));
            es.execute(new ReadThread(list));
            System.out.println("");
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            es.shutdown();

            System.out.println("size:"+list.size());
            es.shutdownNow();
        }

}
