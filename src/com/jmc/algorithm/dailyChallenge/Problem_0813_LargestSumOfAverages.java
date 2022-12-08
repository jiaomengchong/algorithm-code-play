package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/largest-sum-of-averages/
 */
public class Problem_0813_LargestSumOfAverages {
    public static double largestSumOfAverages(int[] nums, int k) {
        double[][] dp = new double[nums.length + 1][k + 1];
        return dfs(nums, 0, k, dp);
    }

    private static double dfs(int[] nums, int index, int k, double[][] dp) {
        if (k == 0) {
            return 0.0;
        }

        if (dp[index][k] != 0.0) {
            return dp[index][k];
        }

        if (k == 1) {
            double sum = 0.0;
            for (int i = index; i < nums.length; i++) {
                sum += nums[i];
            }
            dp[index][k] = sum / (nums.length - index);
            return dp[index][k];
        }

        double sum = 0.0;
        double ans = 0.0;
        for (int i = index; i <= nums.length - k; i++) {
            sum += nums[i];
            double avg = sum / (i - index + 1);
            double dfs = dfs(nums, i + 1, k - 1, dp);
            ans = Math.max(ans, avg + dfs);
        }
        dp[index][k] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{9, 1, 2, 3, 9};
        int k = 3;
        System.out.println(largestSumOfAverages(nums, k));
    }
}
