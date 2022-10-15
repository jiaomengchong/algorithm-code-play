package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/maximum-ascending-subarray-sum/
 */
public class Problem_1800_MaximumAscendingSubarraySum {
    public static int maxAscendingSum(int[] nums) {
        int ans = 0;
        // 输入：nums = [10,20,30,5,10,50]
        // 输出：65
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = nums[0];
        ans = dp[0];
        for (int i = 1; i < N; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{100, 10, 1};
        System.out.println(maxAscendingSum(nums));
    }
}
