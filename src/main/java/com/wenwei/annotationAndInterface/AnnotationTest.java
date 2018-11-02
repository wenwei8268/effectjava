package com.wenwei.annotationAndInterface;
/**
 * Created by wenweizww on 2018/9/5.
 */


/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/9/5
 * description:
 */
public class AnnotationTest {
    @ExceptionTest(ArithmeticException.class)
    public static void m1(){
        int i = 0;
        i = i/i;
    }
    @ExceptionTest(ArithmeticException.class)
    public static void m2(){
        int[] a = new int[0];
        int i = a[1];
    }

}
