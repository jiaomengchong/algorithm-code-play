package com.jmc.algorithm.training001.class02;

/**
 * 中国象棋，马走k步来到坐标为(x,y)
 * 10行*9列
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/16 14:15
 */
public class Test {
    public static int ways1(int x, int y, int k) {

        return process1(x, y, k);
    }

    private static int process1(int x, int y, int k) {
        if (x < 0 || x > 9 || y < 0 || y > 8 || k < 0) {
            return 0;
        }

        if (x == 0 && y == 0 && k == 0) {
            return 1;
        }

        int res = process1(x - 1, y + 2, k - 1)
                + process1(x + 1, y + 2, k - 1)
                + process1(x + 2, y + 1, k - 1)
                + process1(x + 2, y - 1, k - 1)
                + process1(x + 1, y - 2, k - 1)
                + process1(x - 1, y - 2, k - 1)
                + process1(x - 2, y - 1, k - 1)
                + process1(x - 2, y + 1, k - 1);
        return res;
    }

    public static int ways2(int x, int y, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[0][0][0] = 1;
        for (int K = 1; K <= k; K++) {
            for (int X = 0; X < 10; X++) {
                for (int Y = 0; Y < 9; Y++) {
                    dp[X][Y][K] = getValue(dp, X - 1, Y + 2, K - 1)
                            + getValue(dp, X + 1, Y + 2, K - 1)
                            + getValue(dp, X + 2, Y + 1, K - 1)
                            + getValue(dp, X + 2, Y - 1, K - 1)
                            + getValue(dp, X + 1, Y - 2, K - 1)
                            + getValue(dp, X - 1, Y - 2, K - 1)
                            + getValue(dp, X - 2, Y - 1, K - 1)
                            + getValue(dp, X - 2, Y + 1, K - 1);
                }
            }
        }

        return dp[x][y][k];
    }

    public static int getValue(int[][][] dp, int x, int y, int k) {
        if (x < 0 || x > 9 || y < 0 || y > 8 || k < 0) {
            return 0;
        }

        return dp[x][y][k];
    }

    public static void main(String[] args) {
        int x = 4, y = 5, k = 10;
        System.out.println(ways1(x, y, k));
        System.out.println(ways2(x, y, k));
    }
}
