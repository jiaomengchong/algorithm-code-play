package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-operations-to-make-the-array-increasing/
 */
public class Problem_1827_MinimumOperationsToMakeTheArrayIncreasing {
    public static int minOperations(int[] nums) {
        int ans = 0;
        int N = nums.length;
        int pre = nums[0];
        for (int i = 1; i < N; i++) {
            if (nums[i] <= pre) {
                ans += pre - nums[i] + 1;
                pre++;
            } else {
                pre = nums[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{8};
        System.out.println(minOperations(nums));
    }
}
