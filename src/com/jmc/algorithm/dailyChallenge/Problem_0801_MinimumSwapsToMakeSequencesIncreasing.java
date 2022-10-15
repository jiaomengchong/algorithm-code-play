package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing/
 */
public class Problem_0801_MinimumSwapsToMakeSequencesIncreasing {
    public static int minSwap(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int[][] dp = new int[N][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        for (int i = 1; i < N; i++) {
            int a1 = nums1[i - 1], a2 = nums1[i];
            int b1 = nums2[i - 1], b2 = nums2[i];
            if ((a2 > a1 && b2 > b1) && (a2 > b1 && b2 > a1)) {
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = dp[i][0] + 1;
            } else if (a2 > a1 && b2 > b1) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + 1;
            } else {
                dp[i][0] = dp[i - 1][1];
                dp[i][1] = dp[i - 1][0] + 1;
            }
        }
        return Math.min(dp[N - 1][0], dp[N - 1][1]);
    }
}
