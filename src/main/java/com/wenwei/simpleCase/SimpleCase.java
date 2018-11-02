package com.wenwei.simpleCase;
/**
 * Created by wenweizww on 2018/9/15.
 */


import org.junit.Test;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/9/15
 * description:
 */
public class SimpleCase {

    @Test
    public void testInteger(){
        Integer inter1 = new Integer(2199);
        Integer inter2=2199;
        int inter3=2199;

        System.out.println(inter1 == inter2);
        System.out.println(inter1 == inter3);
        System.out.println(inter2 == inter3);
    }
    @Test
    public void testInteger1(){
//        Integer inter1 = new Integer(2199);
        Integer inter2=2199,inter1 = 2199,integer4 = 21;
        int inter3=21;

        System.out.println(inter1 == inter2);
        System.out.println(integer4 == inter3);
//        System.out.println(inter2 == inter3);
    }
 @Test
    public void testMathException1(){
//        Integer inter1 = new Integer(2199);
        Integer inter2=2199,inter1 = 2199,integer4 = 21;
        int inter3=21;
        int i = 1;
        try {

            int j = i/0;
        }catch (ArithmeticException e ){
            System.out.println("test ArithmeticException");
        }catch (Exception e ){
            System.out.println("test exception");
        }finally {
            System.out.println("hello world finally");
        }

        System.out.println(inter1 == inter2);
        System.out.println(integer4 == inter3);
//        System.out.println(inter2 == inter3);
    }


}
