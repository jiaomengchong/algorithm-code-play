package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/jump-game/
 */
public class Problem_0055_JumpGame {
    public static boolean canJump(int[] nums) {
        // 输入：nums = [2,3,1,1,4]
        // 输出：true
        int[] dp = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        return dfs(nums, 0, dp);
    }

    private static boolean dfs(int[] nums, int index, int[] dp) {
        if (index == nums.length) {
            return true;
        }

        if (dp[index] != Integer.MAX_VALUE) {
            return dp[index] == 1;
        }

        if (nums[index] == 0) {
            return index == nums.length - 1;
        }

        for (int i = 1; i <= nums[index]; i++) {
            if (dfs(nums, index + i, dp)) {
                dp[index + i] = 1;
                return true;
            }
        }
        dp[index] = 0;
        return false;
    }

    public static boolean canJump1(int[] nums) {
        // 输入：nums = [2,3,1,1,4]
        // 输出：true
        int mostRight = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= mostRight) {
                mostRight = Math.max(mostRight, i + nums[i]);
                if (mostRight >= nums.length - 1) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 0, 4};
        System.out.println(canJump(nums));
        System.out.println(canJump1(nums));
    }
}
