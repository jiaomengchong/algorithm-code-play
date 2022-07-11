package com.jmc.algorithm.dailyChallenge;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * 测试链接：https://leetcode.cn/problems/find-lucky-integer-in-an-array/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/11 15:16
 **/
public class Problem_1394_FindLuckyIntegerInAnArray {
    public static int findLucky(int[] arr) {
        TreeMap<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int key : map.keySet()) {
            if (key == map.get(key)) {
                return key;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 2, 3, 3, 3};
        System.out.println(findLucky(arr));
    }
}
