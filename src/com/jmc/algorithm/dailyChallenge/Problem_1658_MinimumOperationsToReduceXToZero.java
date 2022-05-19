package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/
 */
public class Problem_1658_MinimumOperationsToReduceXToZero {
    public static int minOperations(int[] nums, int x) {
        int N = nums.length;
        int[][][] dp = new int[N][N][x + 1];
        int ans = process(nums, 0, nums.length - 1, x, dp);
        return ans != Integer.MAX_VALUE ? ans : -1;
    }

    private static int process(int[] nums, int left, int right, int rest, int[][][] dp) {
        if (left == right) {
            return rest == 0 ? 0 : (rest == nums[left] ? 1 : Integer.MAX_VALUE);
        }

        if (rest == 0) {
            return 0;
        }

        if (nums[left] > rest && nums[right] > rest) {
            return Integer.MAX_VALUE;
        }

        if (dp[left][right][rest] != 0) {
            return dp[left][right][rest];
        }

        int p1 = process(nums, left + 1, right, rest - nums[left], dp);
        int p2 = process(nums, left, right - 1, rest - nums[right], dp);
        if (p1 != Integer.MAX_VALUE) {
            p1 += 1;
        }
        if (p2 != Integer.MAX_VALUE) {
            p2 += 1;
        }
        dp[left][right][rest] = Math.min(p1, p2);
        return Math.min(p1, p2);
    }

    public static int minOperations1(int[] nums, int x) {

        return 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 20, 3, 4, 3};
        int x = 8;
        System.out.println(minOperations(nums, x));
    }
}
