package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/the-masseuse-lcci/
 */
public class Problem_17Dot16_TheMasseuseLcci {
    public static int massage(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int N = nums.length;
        int[] newNums = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            newNums[i] = nums[i - 1];
        }
        int[][] dp = new int[N + 2][N + 2];
        for (int i = 0; i <= N + 1; i++) {
            for (int j = 0; j <= N + 1; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }
        return process(newNums, 0, 0, dp);
    }

    private static int process(int[] nums, int index, int pre, int[][] dp) {
        if (index == nums.length) {
            return 0;
        }
        if (dp[index][pre] != Integer.MIN_VALUE) {
            return dp[index][pre];
        }
        int p1 = process(nums, index + 1, pre, dp);
        int p2 = 0;
        if (pre != index - 1 || pre == 0) {
            p2 = process(nums, index + 1, index, dp) + nums[index];
        }
        dp[index][pre] = Math.max(p1, p2);
        return Math.max(p1, p2);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 1, 4, 5, 3, 1, 1, 3};
        System.out.println(massage(nums));
    }
}
