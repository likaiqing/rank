package com.pandatv.tools.lru;

import java.util.HashMap;

/**
 * @author: likaiqing
 * @create: 2018-08-31 18:03
 **/
public class LRUCache2<K, V> {
    private final int MAX_CACHE_SIZE;
    private Entry<K, V> first;
    private Entry<K, V> last;

    private HashMap<K, Entry<K, V>> hashMap;

    public LRUCache2(int max_cache_size) {
        MAX_CACHE_SIZE = max_cache_size;
        hashMap = new HashMap<K, Entry<K, V>>();
    }

    public void put(K key, V value) {
        Entry entry = getEntry(key);
        if (null == entry) {
            if (hashMap.size() >= MAX_CACHE_SIZE) {
                remove(last.key);
            }
            entry = new Entry();
            entry.key = key;
        }
        entry.value = value;
        moveToFirst(entry);
        hashMap.put(key, entry);
    }

    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null) return null;
        moveToFirst(entry);
        return entry.value;
    }

    private void moveToFirst(Entry entry) {
        if (entry == first) return;
        if (entry.pre != null) entry.pre.next = entry.next;
        if (entry.next != null) entry.next.pre = entry.pre;
        if (entry == last) last = entry.pre;

        if (first == null || last == null) {
            first = last = entry;
            return;
        }
        entry.next = first;
        first.pre = entry;
        first = entry;
        entry.pre = null;
    }

    public void remove(K key) {
        Entry entry = getEntry(key);
        if (entry != null) {
            if (entry.pre != null) entry.pre.next = entry.next;
            if (entry.next != null) entry.next.pre = entry.pre;
            if (entry == first) first = entry.next;
            if (entry == last) last = entry.pre;
        }
        hashMap.remove(key);
    }

    private Entry getEntry(K key) {
        return hashMap.get(key);
    }

    class Entry<K, V> {
        public Entry pre;
        public Entry next;
        public K key;
        public V value;
    }
}
