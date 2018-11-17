package com.wenwei.simpleMath;
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
public class simpleMathCase {
    /**
    *@description:  2的幂次方
    *@author: wenwei
    *@params: 
    *@returns: 
    *@date: 2018/11/17 5:19 PM
    **/
    
    @Test
    public void testMath(){
        for (int i = 0; i <20 ; i++) {
//            (val & -val) == val;
            System.out.println("-"+i+" 的16进制"+ Integer.toHexString(-i));
            //toOctalString 八进制
            //tohexString 16进制
            System.out.println(i+" 与 -"+i+"16进制的结果是："+ Integer.toOctalString(i & -i));
//            System.out.println("i & -i "+ Integer.toHexString(i & -i));

            System.out.println(i+" 是否为2的幂次方"+ ((i & -i) == i));
//
//            if((i & -i) == i){
//                //如果是2的幂次方
//                i+1
//            }
        }
    }
}
