package com.jmc.algorithm.greatOffer.class34;

import java.util.HashMap;
import java.util.Map;

/**
 * o(1)插入、删除和获取随机元素
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/4 0:12
 **/
public class Problem_0380_InsertDeleteGetRandom {
    public static class RandomizedSet {
        private Map<Integer, Integer> keyIndexMap;
        private Map<Integer, Integer> indexKeyMap;
        private int size;

        public RandomizedSet() {
            keyIndexMap = new HashMap<>();
            indexKeyMap = new HashMap<>();
            size = 0;
        }

        public boolean insert(int val) {
            if (!keyIndexMap.containsKey(val)) {
                keyIndexMap.put(val, size);
                indexKeyMap.put(size++, val);
                return true;
            }
            return false;
        }

        public boolean remove(int val) {
            if (keyIndexMap.containsKey(val)) {
                Integer deleteIndex = keyIndexMap.get(val);
                Integer lastKey = indexKeyMap.get(--size);
                keyIndexMap.put(lastKey, deleteIndex);
                indexKeyMap.put(deleteIndex, lastKey);
                keyIndexMap.remove(val);
                indexKeyMap.remove(size);
                return true;
            }
            return false;
        }

        public int getRandom() {
            if (size == 0) {
                return -1;
            }
            int index = (int) (Math.random() * size);
            return indexKeyMap.get(index);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;

        int total = 2000000;
        for (int i = 0; i < total; i++) {
            int index = (int) (Math.random() * arr.length);
            if (index == 0) {
                count0++;
            }
            if (index == 1) {
                count1++;
            }
            if (index == 2) {
                count2++;
            }
        }
        System.out.println(String.format("count0:%s, count1:%s, count2:%s", (double) count0 / total, (double) count1 / total, (double) count2 / total));

        // 初始化一个空的集合。
        RandomizedSet randomSet = new RandomizedSet();

        // ["RandomizedSet","insert","remove","insert","getRandom","remove","insert","getRandom"]
        // [[],[1],[2],[2],[],[1],[2],[]]
        // true false true 1/2 true false 2
        System.out.println(randomSet.insert(1));
        System.out.println(randomSet.remove(2));
        System.out.println(randomSet.insert(2));
        System.out.println(randomSet.getRandom());
        System.out.println(randomSet.remove(1));
        System.out.println(randomSet.insert(2));
        System.out.println(randomSet.getRandom());
    }
}
