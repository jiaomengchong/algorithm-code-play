package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/JEj789/
 */
public class OfferII_0091_PaintHouse {
    public static int minCost(int[][] costs) {
        int N = costs.length;
        int[][][] dp = new int[N + 1][2001][3];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= 2000; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        return process(costs, 0, 0, -1, dp);
    }

    private static int process(int[][] costs, int index, int preCost, int color, int[][][] dp) {
        if (index == costs.length) {
            return 0;
        }

        if (color != -1 && dp[index][preCost][color] != Integer.MAX_VALUE) {
            return dp[index][preCost][color];
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= 2; i++) {
            if (color == i) {
                continue;
            }
            ans = Math.min(ans, process(costs, index + 1, preCost + costs[index][i], i, dp) + costs[index][i]);
        }

        if (color != -1) {
            dp[index][preCost][color] = ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] costs = {{8,12,18},{14,6,8},{10,9,13},{2,17,14},{18,18,6},{2,1,15},{19,20,2},{18,15,16},{20,18,18},{15,10,10},{2,20,18},{14,5,15},{18,10,12},{9,17,19},{9,1,6},{4,4,10},{7,8,15},{16,17,4},{16,16,13},{12,7,10},{14,13,8},{16,6,18},{10,5,10},{3,5,11},{9,9,6},{10,15,19},{4,5,19},{12,17,17}};
        System.out.println(minCost(costs));
    }
}
