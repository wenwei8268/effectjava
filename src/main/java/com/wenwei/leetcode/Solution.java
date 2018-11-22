package com.wenwei.leetcode;
/**
 * Created by wenweizww on 2018/11/21.
 */


import org.junit.Test;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/11/21
 * description:
 */
public class Solution {


    @Test
    public void testStr(){
        System.out.println(strStr("aa1234","aa1234"));
    }

    //找出haystack中含有needle的值，并指出其位置
    public int strStr(String haystack, String needle) {
        //needle为空是，return 0
        if(needle.length()<1) return 0;
        //当needl的长度比haystack 还要长，则返回-1
        if(needle.length()>haystack.length()) return -1;
        int returnValue = 0;
        char[] haystackArray=haystack.toCharArray();
        char[] needleArray=needle.toCharArray();
        //遍历string中的数组
        for(int i = 0;i < haystackArray.length; i++){
            //注意此游标的意义，保证needle中的每一个是haystack中的元素
            int t = 0;
            for(int j = 0;j < needleArray.length;j++ ){
                if(haystackArray[i] == needleArray[j]){
                    t++;
                    if(t == needleArray.length){
                        return i;
                    }
                    continue;
                }
            }


        }
        return -1;

    }
}
