package com.wenwei.lambdasAndStreams;
/**
 * Created by wenweizww on 2018/9/8.
 */


import java.util.function.DoubleBinaryOperator;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/9/8
 * description:
 */
public enum Operationlamabdas {
    PULSS("+", (x,y) -> x + y ),
    MINUSS("+", (x,y) -> x - y );

    private final String s ;
    private final DoubleBinaryOperator op ;

    Operationlamabdas(String s, DoubleBinaryOperator op) {
        this.s = s;
        this.op = op;
    }
}
