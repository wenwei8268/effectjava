package com.wenwei.simpleCase;
/**
 * Created by wenweizww on 2018/10/2.
 */


import org.junit.Test;

import java.util.Arrays;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/10/2
 * description:
 */
public class SortClass {


    //冒泡排序的原理->升序原理
    /***每次取到 最小的值
     * 时间复杂度o(n2)
     * 空间复杂度o(n)
    **/
    @Test
    public void bubbleSort(){
        int[] int_array = {4,2,1,2,3,5,2,4,5,6,7,8,9};
        //暂存该次比对的最小值；
        int min = 4;
        for(int i = 0;i<int_array.length;i++){
            min=int_array[i];
            for(int j = i;j<int_array.length;j++){
                if(int_array[j]<min) {
                    //交换并且移位
                    int temp =min;
                    min = int_array[j];
                    int_array[j] = temp;
                }
            }
            int_array[i] = min;
        }
        Arrays.stream(int_array).forEach(System.out::print);
    }

    //快速排序->升序原理
    /***每次取到 最小的值
     * 时间复杂度o(n2)
     * 空间复杂度o(n)
     **/
    @Test
    public void quickSort(){
//        int[] int_array = {4,2,1,2,3,5,2,4,5,6,7,8,9};
//        //暂存该次比对的最小值；
//        int min = 4;
//        for(int i = 0;i<int_array.length;i++){
//            min=int_array[i];
//            for(int j = i;j<int_array.length;j++){
//                if(int_array[j]<min) {
//                    //交换并且移位
//                    int temp =min;
//                    min = int_array[j];
//                    int_array[j] = temp;
//                }
//            }
//            int_array[i] = min;
//        }
//        Arrays.stream(int_array).forEach(System.out::print);
        int i = 0;
        int j =5;
        int k = (i+j)/2;
        System.out.println(i+"***"+j+k);
    }



    void quickSort(int[] int_arrays){
        int arr_length=int_arrays.length;
        int low = int_arrays[0],high = int_arrays[arr_length-1];
        if(low<high){
            int middle =  (high+low)/2;


        }
    }
}
