package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/maximum-product-difference-between-two-pairs/
 */
public class Problem_1913_MaximumProductDifferenceBetweenTwoPairs {
    public static int maxProductDifference(int[] nums) {
        int N = nums.length;
        Arrays.sort(nums);
        return nums[N - 1] * nums[N - 2] - nums[0] * nums[1];
    }

    public static int maxProductDifference1(int[] nums) {
        int N = nums.length;
        int max1 = Math.max(nums[0], nums[1]);
        int max2 = Math.min(nums[0], nums[1]);
        int min1 = Math.min(nums[0], nums[1]);
        int min2 = Math.max(nums[0], nums[1]);
        for (int i = 2; i < N; i++) {
            int num = nums[i];
            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }

            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        return max1 * max2 - min1 * min2;
    }

    public static void main(String[] args) {
        // 输入：nums = [5,6,2,7,4]
        // 输出：34
        int[] nums = new int[]{10, 10, 10, 10};
        System.out.println(maxProductDifference(nums));
        System.out.println(maxProductDifference1(nums));
    }
}
