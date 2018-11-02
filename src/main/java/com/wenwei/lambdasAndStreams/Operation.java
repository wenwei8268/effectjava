package com.wenwei.lambdasAndStreams;

/**
 * Created by wenweizww on 2018/9/8.
 */
public enum Operation {
    PLUS1("+"){
        public double apply(double x ,double y){
            return x+y;
        }
    },MINUS("-"){
        public double apply(double x ,double y){
            return x-y;
        }
    },TMES("+"){
        public double apply(double x ,double y){
            return x*y;
        }
    };
    private final String string;
    Operation(String s) {
        this.string =s;
    }
    public abstract double apply(double x,double y);
}
