package com.jmc.algorithm.greatOffer.class02;

import java.util.HashMap;

/**
 * 设计有setAll功能的哈希表
 * put、get、setAll方法，时间复杂度O(1)
 *
 * @author jmc
 * @version 1.0
 * @date 2021/4/29 14:47
 */
public class Code05_SetAll {
    public static class MyValue<V> {
        private V value;
        private long time;

        public MyValue(V value, long time) {
            this.value = value;
            this.time = time;
        }
    }

    public static class MyHashMap<K, V> {
        private HashMap<K, MyValue<V>> map;
        private long time;
        private MyValue<V> setAll;

        public MyHashMap() {
            map = new HashMap<>();
            time = 0;
            setAll = new MyValue<>(null, -1);
        }

        public void setAll(V value) {
            setAll = new MyValue<>(value, time++);
        }

        public void put(K key, V value) {
            map.put(key, new MyValue<>(value, time++));
        }

        public V get(K key) {
            if (!map.containsKey(key)) {
                return null;
            }
            if (map.get(key).time > setAll.time) {
                return map.get(key).value;
            } else {
                return setAll.value;
            }
        }
    }
}
