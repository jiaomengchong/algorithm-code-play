package com.jmc.algorithm.weekProblem.class_2021_11_4_week;

import java.util.HashMap;
import java.util.Map;

/**
 * 猜数字大小II
 * 测试链接：https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/
 *
 * @Author jmc
 * @Description
 * @Date 2021/11/29 14:11
 **/
public class Code02_GuessNumberHigherOrLowerII {
    public static int getMoneyAmount(int n) {
        if (n == 0 || n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }

        return process(1, n);
    }

    private static int process(int L, int R) {
        if (L == R) {
            return 0;
        }
        if (L == R - 1) {
            return L;
        }
        int ans = Math.min(L + process(L + 1, R), R + process(L, R - 1));
        for (int M = L + 1; M < R; M++) {
            ans = Math.min(ans, M + Math.max(process(L, M - 1), process(M + 1, R)));
        }
        return ans;
    }

    public static int getMoneyAmount0(int n) {
        if (n == 0 || n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }

        Map<String, Integer> dp = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp.put(String.format("%s_%s", i, j), -1);
            }
        }
        return process0(1, n, dp);
    }

    private static int process0(int L, int R, Map<String, Integer> dp) {
        if (dp.get(String.format("%s_%s", L, R)) != -1) {
            return dp.get(String.format("%s_%s", L, R));
        }
        int ans;
        if (L == R) {
            ans = 0;
        } else if (L == R - 1) {
            ans = L;
        } else {
            ans = Math.min(L + process0(L + 1, R, dp), R + process0(L, R - 1, dp));
            for (int M = L + 1; M < R; M++) {
                ans = Math.min(ans, M + Math.max(process0(L, M - 1, dp), process0(M + 1, R, dp)));
            }
        }

        dp.put(String.format("%s_%s", L, R), ans);
        return ans;
    }

    public static int getMoneyAmount1(int n) {
        if (n == 0 || n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }

        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i < n; i++) {
            dp[i][i + 1] = i;
        }

        for (int L = n - 2; L > 0; L--) {
            for (int R = L + 2; R <= n; R++) {
                dp[L][R] = Math.min(L + dp[L + 1][R], R + dp[L][R - 1]);
                for (int M = L + 1; M < R; M++) {
                    dp[L][R] = Math.min(dp[L][R], M + Math.max(dp[L][M - 1], dp[M + 1][R]));
                }
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int n = 115;
        System.out.println(getMoneyAmount1(n));
        System.out.println(getMoneyAmount0(n));
    }
}
