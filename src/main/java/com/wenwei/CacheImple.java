package com.wenwei;
/**
 * Created by wenweizww on 2018/9/11.
 */


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/9/11
 * description:
 */
public class CacheImple<K,V> {
    private Map<K, V> cache;
    private int capacity;

    public enum POLICY {
        LRU, FIFO
    }

    public CacheImple(int cap, POLICY policy) {
        this.capacity = cap;
        cache = new LinkedHashMap<K, V>(cap, 0.75f, policy.equals(POLICY.LRU)){
            //超出容量就删除最老的值
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    public V get(K key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        return null;
    }

    public void set(K key, V val) {
        cache.put(key, val);
    }

    public void printKV() {
        System.out.println("key value in cache");
        for (Map.Entry<K,V> entry : cache.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    public static void main(String[] args) {
        CacheImple<Integer, String> cache = new CacheImple(5, POLICY.LRU);

        cache.set(1, "first");
        cache.set(2, "second");
        cache.set(3, "third");
        cache.set(4, "fourth");
        cache.set(5, "fifth");
        cache.printKV();

        cache.get(1);
        cache.get(2);
        cache.printKV();

        cache.set(6, "sixth");
        cache.printKV();
    }
}