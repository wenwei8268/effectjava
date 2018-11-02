package com.wenwei.map;
/**
 * Created by wenweizww on 2018/9/15.
 */


import com.wenwei.CacheImple;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/9/15
 * description:
 */
public class BinarySearchTree<K,V>  {
    private Map<K, V> cache;
    private int capacity;

    public enum POLICY {
        LRU, FIFO
    }

    public BinarySearchTree(Map<K, V> cache) {
        this.cache = cache;
    }
    public BinarySearchTree(int capacity,POLICY policy){
        this .capacity = capacity;
        cache = new LinkedHashMap<K, V>(capacity,0.75f,policy.equals(CacheImple.POLICY.LRU)){
           //return false;
        };
    }


    //    @Test
//    public void testSqrt(){
//        LinkedHashMap<Integer,User> linkedHashMap = new LinkedHashMap(5,0.75f,false);
//
//        linkedHashMap.put(1,new User("wenwei",1));
//        linkedHashMap.put(2,new User("wenwei",2));
//        linkedHashMap.put(3,new User("wenwei",3));
//        linkedHashMap.put(4,new User("wenwei",4));
//        linkedHashMap.put(5,new User("wenwei",5));
//
//        System.out.println(linkedHashMap.toString());
//        linkedHashMap.get(1);
//
//        linkedHashMap.put(1,new User("wenwe1i",1));
//        System.out.println(linkedHashMap.toString());
//        linkedHashMap.put(4,new User("wen2wei",4));
//        System.out.println(linkedHashMap.toString());
//        linkedHashMap.put(6,new User("we3nwei",6));
//        System.out.println(linkedHashMap.toString());
//    }
}
