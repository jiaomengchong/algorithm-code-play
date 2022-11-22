package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/champagne-tower/
 */
public class Problem_0799_ChampagneTower {
    public static double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[query_row + 2][query_row + 2];
        dp[1][1] = poured;
        for (int i = 2; i <= query_row + 1; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1] - 1, 0) / 2 + Math.max(dp[i - 1][j] - 1, 0) / 2;
            }
        }
        return Math.min(dp[query_row + 1][query_glass + 1], 1);
    }

    public static void main(String[] args) {
        int poured = 25, query_glass = 6, query_row = 1;
        System.out.println(champagneTower(poured, query_glass, query_row));
    }
}
