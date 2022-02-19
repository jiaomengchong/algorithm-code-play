package com.jmc.algorithm.greatOffer.class09;

/**
 * 测试链接：https://leetcode-cn.com/problems/longest-increasing-subsequence/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/3 16:17
 */
public class Code03_LongestIncreasingSubsequence {
    public static int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, process1(nums, i, i));
        }

        return ans;
    }

    private static int process1(int[] nums, int index, int preIndex) {
        if (index == nums.length) {
            return 0;
        }
        if (index == preIndex) {
            return 1 + process1(nums, index + 1, preIndex);
        }
        int ans = 0;
        if (nums[preIndex] < nums[index]) {
            int p1 = process1(nums, index + 1, index) + 1;
            int p2 = process1(nums, index + 1, preIndex);
            ans += Math.max(p1, p2);
        } else {
            ans += process1(nums, index + 1, preIndex);
        }

        return ans;
    }

    public static int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int N = nums.length;
        int ans = Integer.MIN_VALUE;
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = -1;
            }
        }

        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, process2(nums, i, i, dp));
        }

        return ans;
    }

    private static int process2(int[] nums, int index, int preIndex, int[][] dp) {
        if (dp[index][preIndex] != -1) {
            return dp[index][preIndex];
        }
        if (index == nums.length) {
            return 0;
        }
        if (preIndex == index) {
            return 1 + process2(nums, index + 1, preIndex, dp);
        }

        int ans = 0;
        if (nums[preIndex] < nums[index]) {
            int p1 = process2(nums, index + 1, index, dp) + 1;
            int p2 = process2(nums, index + 1, preIndex, dp);
            ans += Math.max(p1, p2);
        } else {
            ans += process2(nums, index + 1, preIndex, dp);
        }

        dp[index][preIndex] = ans;
        return ans;
    }

    public static int lengthOfLIS3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int ans = 1;
        int N = nums.length;
        int l = 0;
        int r = 0;
        int right = 0;
        int m = 0;
        int[] ends = new int[N];
        ends[0] = nums[0];
        for (int i = 1; i < N; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (nums[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = nums[i];
            ans = Math.max(ans, l + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{4, 10, 4, 3, 8, 9};
//        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums = new int[]{7, 7, 7, 7, 7, 7, 7};
        System.out.println(lengthOfLIS1(nums));
        System.out.println(lengthOfLIS2(nums));
        System.out.println(lengthOfLIS3(nums));
    }
}
