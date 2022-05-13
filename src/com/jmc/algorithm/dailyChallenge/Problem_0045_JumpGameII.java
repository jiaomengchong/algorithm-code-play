package com.jmc.algorithm.dailyChallenge;

public class Problem_0045_JumpGameII {

    public static int jump(int[] nums) {
        // [2,3,1,1,4]
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int N = nums.length;
        int[][] dp = new int[N][N];
        /*for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }*/

        return process(nums, 0, nums.length - 1, dp);
    }

    private static int process(int[] nums, int index, int rest, int[][] dp) {
        if (rest == 0) {
            return 0;
        }

        if (index == nums.length - 1) {
            return 0;
        }

        if (dp[index][rest] != 0) {
            return dp[index][rest];
        }

        if (nums[index] == 0) {
            return Integer.MAX_VALUE;
        }

        int p1 = process(nums, index + 1, rest - 1, dp);
        if (p1 != Integer.MAX_VALUE) {
            p1 += 1;
        }
        int p2 = Integer.MAX_VALUE;
        for (int i = 1; i <= nums[index]; i++) {
            int next = process(nums, index + i, rest - i >= 0 ? rest - i : 0, dp);
            p2 = Math.min(p2, next + (next != Integer.MAX_VALUE ? 1 : 0));
        }

        int ans = Math.min(p1, p2);
        dp[index][rest] = ans;
        return ans;
    }

    public static int jump1(int[] nums) {
        // [2,3,1,1,4]
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int N = nums.length;
        int[][] dp = new int[N][N];
        for (int index = N - 2; index >= 0; index--) {
            for (int rest = 1; rest < N; rest++) {
                int p1 = dp[index + 1][rest - 1];
                if (nums[index] != 0) {
                    p1 += 1;
                } else {
                    p1 = Integer.MAX_VALUE;
                }
                int p2 = Integer.MAX_VALUE;
                for (int i = 1; i <= nums[index]; i++) {
                    if (index + i < N) {
                        int next = dp[index + i][rest - i >= 0 ? rest - i : 0];
                        p2 = Math.min(p2, next + (next != Integer.MAX_VALUE ? 1 : 0));
                    }
                }
                dp[index][rest] = Math.min(p1, p2);
            }
        }

        return dp[0][N - 1];
    }

    public static int jump2(int[] nums) {
        // 输入: nums = [2,3,1,1,4]
        // 输出: 2
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(nums[i] + i, maxPosition);
            if (i == end) {
                end = Math.min(maxPosition, nums.length - 1);
                steps++;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,0,1,4};
        System.out.println(jump(nums));
        System.out.println(jump1(nums));
    }
}
