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

//    private static int getKthNum(int[] nums, int k) {
//        int[] arr = copyOfNums(nums);
//        return process(arr, 0, arr.length - 1, k - 1);
//    }
//
//    private static int process(int[] arr, int L, int R, int k) {
//        int pivot = L + (int)(Math.random() * (R - L + 1));
//        int[] ranges = partition(arr, L, R, arr[pivot]);
//        if (ranges[0] <= k && k <= ranges[1]) {
//            return arr[ranges[0]];
//        } else if (k < ranges[0]) {
//            return process(arr, L, ranges[0] - 1, k);
//        } else {
//            return process(arr, ranges[1] + 1, R, k);
//        }
//    }
//
//    private static int[] partition(int[] arr, int l, int r, int pivot) {
//        int less = l - 1;
//        int more = r + 1;
//        int cur = l;
//        if (l == r) {
//            return new int[]{l, l};
//        }
//
//        // 2,1,2,5,3,2
//        while (cur < more) {
//            if (arr[cur] < pivot) {
//                swap(arr, ++less, cur++);
//            } else if (arr[cur] > pivot) {
//                swap(arr, --more, cur);
//            } else {
//                cur++;
//            }
//        }
//        return new int[]{less + 1, more - 1};
//    }
//
//    private static void swap(int[] arr, int i, int j) {
//        int tmp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = tmp;
//    }
//
//    private static int[] copyOfNums(int[] nums) {
//        int[] arr = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            arr[i] = nums[i];
//        }
//        return arr;
//    }

    public static void main(String[] args) {
        int[] nums = new int[]{9,5,3,3};
        System.out.println(repeatedNTimes(nums));
        System.out.println(repeatedNTimes2(nums));
    }
}
