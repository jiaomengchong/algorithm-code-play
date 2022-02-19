package com.jmc.algorithm.dailyChallenge;

/**
 * 完全平方和
 * 测试链接：https://leetcode-cn.com/problems/perfect-squares/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/12 10:39
 */
public class day_06_11 {
    public static int numSquares(int n) {
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = (i + 1) * (i + 1);
        }
        int result = process(values, 0, n);
        return result;
    }

    private static int process(int[] values, int index, int rest) {
        if (rest == 0) {
            return 0;
        }
        if (index == values.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        for (int times = 0; times <= values.length; times++) {
            int p1 = process(values, index + 1, rest - values[index] * times);
            if (p1 != Integer.MAX_VALUE) {
                p1 += 1 * times;
                ans = Math.min(ans, p1);
            }
        }
        return ans;
    }

    public static int numSquares1(int n) {
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = (i + 1) * (i + 1);
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }
        return process1(values, 0, n, dp);
    }

    private static int process1(int[] values, int index, int rest, int[][] dp) {
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        if (rest == 0) {
            dp[index][rest] = 0;
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        if (index == values.length) {
            ans = rest == 0 ? 0 : Integer.MAX_VALUE;
            dp[index][rest] = ans;
            return ans;
        }
        if (rest < 0) {
            dp[index][rest] = Integer.MAX_VALUE;
            return Integer.MAX_VALUE;
        }
        for (int times = 0; times <= values.length; times++) {
            int temp = rest - values[index] * times;
            if (temp >= 0) {
                int p = process1(values, index + 1, temp, dp);
                if (p != Integer.MAX_VALUE) {
                    p += 1 * times;
                    ans = Math.min(ans, p);
                }
            }
        }
        dp[index][rest] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int n = 13;
        System.out.println(numSquares(n));
        System.out.println(numSquares1(n));
    }
}
