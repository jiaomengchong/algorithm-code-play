package com.jmc.algorithm.greatOffer.class44;

import java.util.HashMap;
import java.util.Map;

/**
 * K个不同整数的子数组
 *
 * @Author jmc
 * @Description
 * @Date 2021/11/10 19:30
 **/
public class Problem_0992_SubArraysWithKDifferentIntegers {
    // [1,2,1,3,4], K = 3, res = 3
    public static int subarraysWithKDistinct(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int N = nums.length;
        int ans = 0;
        Map<Integer, Integer> mapK = new HashMap<>();
        Map<Integer, Integer> mapLessK = new HashMap<>();
        int kLeftIndex = 0;
        int lessKLeftIndex = 0;
        for (int i = 0; i < N; i++) {
            if (mapK.containsKey(nums[i])) {
                mapK.put(nums[i], mapK.get(nums[i]) + 1);
            } else {
                mapK.put(nums[i], 1);
            }

            if (mapLessK.containsKey(nums[i])) {
                mapLessK.put(nums[i], mapLessK.get(nums[i]) + 1);
            } else {
                mapLessK.put(nums[i], 1);
            }

            while (mapK.size() > k) {
                if (mapK.get(nums[kLeftIndex]) == 1) {
                    mapK.remove(nums[kLeftIndex++]);
                } else {
                    mapK.put(nums[kLeftIndex], mapK.get(nums[kLeftIndex++]) - 1);
                }
            }

            while (mapLessK.size() == k) {
                if (mapLessK.get(nums[lessKLeftIndex]) == 1) {
                    mapLessK.remove(nums[lessKLeftIndex++]);
                } else {
                    mapLessK.put(nums[lessKLeftIndex], mapLessK.get(nums[lessKLeftIndex++]) - 1);
                }
            }
            ans += lessKLeftIndex - kLeftIndex;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 1, 3, 4};
        int k = 3;
        System.out.println(subarraysWithKDistinct(nums, k));

        // 4 / 5 向上取整
        int a = 10;
        int b = 5;
        System.out.println((a + b - 1) / b);
    }
}
