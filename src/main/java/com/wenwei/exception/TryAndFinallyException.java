package com.wenwei.exception;
/**
 * Created by wenweizww on 2018/11/17.
 */


import org.junit.Test;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/11/17
 * description:
 */
public class TryAndFinallyException {
    @Test
    public void testFinallyMethod(){
        for (int i = 0; i <10 ; i++) {
            try{
                int j = i*i;
                System.out.println(j);
            }catch (Exception e ){
                System.out.println(e.getMessage());
            }finally {
                System.out.println("finally");
            }
        }
    }
}
