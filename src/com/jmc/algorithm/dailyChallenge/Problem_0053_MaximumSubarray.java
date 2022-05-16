package com.jmc.algorithm.dailyChallenge;

public class Problem_0053_MaximumSubarray {
    public static int maxSubArray(int[] nums) {
        // 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
        // 输出：6
        if (nums.length == 1) {
            return nums[0];
        }
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = nums[0];
        int ans = dp[0];
        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // todo 分治

    public static void main(String[] args) {
        int[] nums = new int[]{-1, -2};
        System.out.println(maxSubArray(nums));
    }
}
