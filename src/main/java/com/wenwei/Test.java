package com.wenwei;
/**
 * Created by wenweizww on 2018/10/12.
 */


import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.imageio.spi.ServiceRegistry;
import java.util.*;
import java.util.concurrent.*;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/10/12
 * description:
 */
public class Test {
    @org.junit.Test
    public void  test(){
         Integer i  = new Integer(1);
         Integer j = new Integer(1);
//         int k = 3;
        System.out.println(i == j);
//        System.out.println(isEqual(1,1));
            // 初始化 Date 对象
            Date date = new Date();

            //c的使用
            System.out.printf("全部日期和时间信息：%tc%n",date);
            //f的使用
            System.out.printf("年-月-日格式：%tF%n",date);
            //d的使用
            System.out.printf("月/日/年格式：%tD%n",date);
            //r的使用
            System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",date);
            //t的使用
            System.out.printf("HH:MM:SS格式（24时制）：%tT%n",date);
            //R的使用
            System.out.printf("HH:MM格式（24时制）：%tR",date);
    }
    public int[] twoSum(int[] nums, int target) {
        int[] ret = {-1};
        if (nums.length <= 0) {
            return ret = new int[]{-1};
        }
//        for()
        List map = new ArrayList<Integer>();
//        for
        for (int i = 0; i < nums.length; i++) {
            map.add(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            int x = target - nums[i];
            //考虑如果6 = 3+3的情况；
            // if(x==nums[i]){
            //     continue;
            // }
            int lastIndex = map.lastIndexOf(x);
            if (i != lastIndex) {
                ret =  new int[]{i, lastIndex};
            } else {
                continue;
            }
            if (map.contains(x)) {
                 ret = new int[]{i, map.indexOf(x)};
                // break;
            }

        }
        return ret;
    }
    @org.junit.Test
    public void test1(){
        Executor excutor = new ThreadPoolExecutor(10, 10, 100L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            }
        });
        //for (int i = 0; i <100000000 ; i++) {
        while (true){
            excutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("testfghjkl;");
                }
            });
        }
        //ServiceRegistry serviceRegistry = new ServiceRegistry();
    }
//    }
}
