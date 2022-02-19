package com.jmc.algorithm.greatOffer.class14;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 请返回arr中，求各个子数组的累加和，是<=K的并且是最大的
 * 返回这个最大的累加和
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/19 10:57
 */
public class Code02_MaxSubArraySumLessOrEqualK {
    public static int getMaxLessOrEqualK(int[] arr, int K) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int ans = Integer.MIN_VALUE;
        int sum = 0;
        TreeSet<Integer> treeSet = new TreeSet();
        treeSet.add(0);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (treeSet.ceiling(sum - K) != null) {
                ans = Math.max(ans, sum - treeSet.ceiling(sum - K));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(8, "我是8");
        treeMap.put(2, "我是2");
        treeMap.put(6, "我是6");
        treeMap.put(1, "我是1");
        treeMap.put(4, "我是4");
        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());
        System.out.println(treeMap.floorKey(4));
        System.out.println(treeMap.ceilingKey(4));
        treeMap.remove(4);
        System.out.println("删除key:4");
        // floorKey:小于等于4
        System.out.println(treeMap.floorKey(4));
        // cellingKey:大于等于4
        System.out.println(treeMap.ceilingKey(4));

        int[] arr = new int[]{10, 1, -3, 18, 6, 4};
        int k = 9;
        System.out.println(getMaxLessOrEqualK(arr, k));
    }
}
