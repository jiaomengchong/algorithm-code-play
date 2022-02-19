package com.jmc.algorithm.weekProblem.class_2021_12_1_week;

/**
 * leetCode 651
 * 测试链接：https://leetcode.com/problems/4-keys-keyboard/
 *
 * @Author jmc
 * @Description
 * @Date 2021/12/6 21:09
 **/
public class Code02_4KeysKeyboard {
    public static int ways1(int n) {
        if (n <= 0) {
            return -1;
        }

        int[] dp = new int[n + 1];
        for (int i = 1; i < 6; i++) {
            dp[i] = i;
        }

        // 12 13 14 15 16 17 18
        // S  A  C  P  P  P  P
        for (int i = 6; i <= n; i++) {
            dp[i] = Math.max(Math.max(2 * dp[i - 3], 3 * dp[i - 4]), Math.max(4 * dp[i - 5], 5 * dp[i - 6]));
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 27;
        System.out.println(ways1(n));
    }
}
