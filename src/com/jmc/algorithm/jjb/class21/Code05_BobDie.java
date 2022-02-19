package com.jmc.algorithm.jjb.class21;

/**
 * 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/16 12:25
 */
public class Code05_BobDie {
    public static double bobWays1(int row, int col, int k, int M, int N) {
        if (M < 0 || N < 0 || row < 0 || col < 0 || k < 0) {
            return 0;
        }

        double all = Math.pow(4, k);
        double p = process1(M, N, row, col, k);

        return p / all;
    }

    private static double process1(int M, int N, int row, int col, int k) {
        if (row < 0 || row >= M || col < 0 || col >= N) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        return process1(M, N, row - 1, col, k - 1) +
                process1(M, N, row + 1, col, k - 1) +
                process1(M, N, row, col - 1, k - 1) +
                process1(M, N, row, col + 1, k - 1);
    }

    public static double dpWays1(int row, int col, int k, int M, int N) {
        if (M < 0 || N < 0 || row < 0 || col < 0 || k < 0) {
            return 0;
        }
        double all = Math.pow(4, k);
        double[][][] dp = new double[M][N][k + 1];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j][0] = 1;
            }
        }

        for (int step = 1; step <= k; step++) {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    dp[i][j][step] = getValue(M, N, i - 1, j, step - 1, dp) +
                            getValue(M, N, i + 1, j, step - 1, dp) +
                            getValue(M, N, i, j - 1, step - 1, dp) +
                            getValue(M, N, i, j + 1, step - 1, dp);
                }
            }
        }

        return dp[row][col][k] / all;
    }

    public static double getValue(int M, int N, int row, int col, int k, double[][][] dp) {
        if (row < 0 || row == M || col < 0 || col == N || k < 0) {
            return 0;
        }

        return dp[row][col][k];
    }

    public static void main(String[] args) {
        System.out.println(bobWays1(6, 6, 10, 50, 50));
        System.out.println(dpWays1(6, 6, 10, 50, 50));
    }
}
