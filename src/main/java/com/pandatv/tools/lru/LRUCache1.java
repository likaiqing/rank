package com.pandatv.tools.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://www.cnblogs.com/lzrabbit/p/3734850.html
 * LRU是Least Recently Used 的缩写，翻译过来就是“最近最少使用”，LRU缓存就是使用这种原理实现，简单的说就是缓存一定量的数据，当超过设定的阈值时就把一些过期的数据删除掉
 * Java里面实现LRU缓存通常有两种选择，一种是使用LinkedHashMap，一种是自己设计数据结构，使用链表+HashMap
 * @author: likaiqing
 * @create: 2018-08-31 17:50
 **/
public class LRUCache1 {
    public static void main(String[] args) {
        int cacheSize = 10;
        /**
         * 此方式简洁；
         * 还可通过继承LinkedHashMap方式，实现方法removeEldestEntry();
         * 还可以在构造器内创建全局变量map对应的LinkedHashMap对象，方式同样如下
         */
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>((int) Math.ceil(cacheSize / 0.75f), 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > cacheSize;
            }
        };
        for (int i = 0; i < 50; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
        for (int i = 0; i < 5; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
        for (int i = 10; i < 15; i++) {
            System.out.println(map.get(String.valueOf(i)));
        }
        System.out.println(map.size());
    }
}
