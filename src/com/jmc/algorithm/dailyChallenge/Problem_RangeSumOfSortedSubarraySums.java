package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/range-sum-of-sorted-subarray-sums/
 */
public class Problem_RangeSumOfSortedSubarraySums {
    public static int rangeSum(int[] nums, int n, int left, int right) {
        int N = nums.length;
        int ans = 0;
        int[] sum = new int[N * (N + 1) / 2];
        int index = 0;
        int mod = 1000000007;
        for (int i = 0; i < N; i++) {
            sum[index++] = nums[i];
            for (int j = i + 1; j < N; j++) {
                sum[index] += sum[index - 1] + nums[j];
                index++;
            }
        }
        Arrays.sort(sum);
        for (int i = left - 1; i < right; i++) {
            ans = (ans + sum[i]) % mod;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        int n = 4, left = 1, right = 10;
        System.out.println(rangeSum(nums, n, left, right));
    }
}
