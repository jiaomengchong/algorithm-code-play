package com.jmc.algorithm.greatOffer.class40;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个数组A和B，长度都是N
 * A[i]不可以在A中和其他数交换，只可以选择和B[i]交换(0<=i<n)
 * 你的目的是让A有序，返回你能不能做到
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/28 18:53
 **/
public class Code04_LetASorted {
    public static boolean ways1(int[] arr1, int[] arr2) {
        if ((arr1 == null || arr1.length == 0) && (arr2 == null || arr2.length == 0)) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }

        return process1(arr1, arr2, 0, arr1[0]);
    }

    private static boolean process1(int[] arr1, int[] arr2, int index, int pre) {
        if (index == arr1.length) {
            return true;
        }

        // 替换
        if (arr1[index] > pre && process1(arr1, arr2, index + 1, arr1[index])) {
            return true;
        }

        // 不替换
        if (arr2[index] > pre && process1(arr1, arr2, index + 1, arr2[index])) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 3, 2};
        int[] arr2 = new int[]{4, 5, 3};
        System.out.println(ways1(arr1, arr2));

        Map<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            if (!hash.containsKey(i % 3)) {
                hash.put(i % 3, 1);
            } else {
                hash.put(i % 3, hash.get(i % 3) + 1);
            }
        }
        for (Integer key : hash.keySet()) {
            System.out.println(String.format("key=%s, value=%s", key, hash.get(key)));
        }
    }
}
