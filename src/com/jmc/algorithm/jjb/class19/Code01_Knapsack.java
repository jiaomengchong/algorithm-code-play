package com.jmc.algorithm.jjb.class19;

/**
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/4 15:31
 */
public class Code01_Knapsack {
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0 || bag < 0) {
            return 0;
        }

        return process(w, v, 0, bag);
    }

    private static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }

        int p1 = process(w, v, index + 1, rest);
        int p2 = process(w, v, index + 1, rest - w[index]);
        if (p2 != -1) {
            p2 += v[index];
        }

        return Math.max(p1, p2);
    }

    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0 || bag < 0) {
            return 0;
        }

        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = bag; rest >= 0; rest--) {
                int p1 = dp[index + 1][rest];
                int p2 = (rest - w[index]) < 0 ? -1 : (dp[index + 1][rest - w[index]] + v[index]);
                dp[index][rest] = Math.max(p1, p2);
            }
        }

        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 14, 11, 12, 13};
        int[] values = {5, 6, 3, 19, 15, 13, 16, 18};
        int bag = 21;

        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }
}
