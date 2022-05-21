package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * 测试链接：https://leetcode.cn/problems/n-repeated-element-in-size-2n-array/
 */
public class Problem_0961_NRepeatedElementInSize2nArray {
    public static int repeatedNTimes(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == nums.length / 2) {
                return key;
            }
        }
        return -1;
    }

    public static int repeatedNTimes2(int[] nums) {
        int n = nums.length;
        Set<Integer> sets = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (!sets.contains(nums[i])) {
                sets.add(nums[i]);
            } else {
                return nums[i];
            }
        }
        return -1;
    }

    public static int repeatedNTimes3(int[] nums) {
        int N = nums.length;
        int index1, index2;
        while (true) {
            index1 = (int) (Math.random() * N);
            index2 = (int) (Math.random() * N);
            if (index1 != index2 && nums[index1] == nums[index2]) {
                return nums[index1];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{9,5,3,3};
        System.out.println(repeatedNTimes(nums));
        System.out.println(repeatedNTimes2(nums));
        System.out.println(repeatedNTimes3(nums));
    }
}
