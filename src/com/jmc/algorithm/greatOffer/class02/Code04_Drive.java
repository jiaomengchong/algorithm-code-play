package com.jmc.algorithm.greatOffer.class02;

import java.util.Arrays;

/**
 * 现有司机N*2人，调度中心会将所有司机平分给A、B两个区域
 * 第 i 个司机去A可得收入为income[i][0]，
 * 第 i 个司机去B可得收入为income[i][1]，
 * 返回所有调度方案中能使所有司机总收入最高的方案，是多少钱
 *
 * @author jmc
 * @version 1.0
 * @date 2021/4/29 9:43
 */
public class Code04_Drive {
    public static int maxMoney1(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }

        int N = income.length;
        return process1(income, 0, N >> 1);
    }

    private static int process1(int[][] income, int index, int rest) {
        int N = income.length;
        if (index == N) {
            return 0;
        }
        if (rest == 0) {
            return income[index][1] + process1(income, index + 1, rest);
        }
        if (N - index == rest) {
            return income[index][0] + process1(income, index + 1, rest - 1);
        }

        int p1 = income[index][0] + process1(income, index + 1, rest - 1);
        int p2 = income[index][1] + process1(income, index + 1, rest);

        return Math.max(p1, p2);
    }

    public static int maxMoney2(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }

        int N = income.length;
        int M = N >> 1;
        int[][] dp = new int[N + 1][M + 1];
        for (int index = N - 1; index > 0; index--) {
            dp[index][0] = income[index][1] + dp[index + 1][0];
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= M; rest++) {
                if (N - index == rest) {
                    dp[index][rest] = income[index][0] + dp[index + 1][rest - 1];
                } else {
                    int p1 = income[index][0] + dp[index + 1][rest - 1];
                    int p2 = income[index][1] + dp[index + 1][rest];
                    dp[index][rest] = Math.max(p1, p2);
                }
            }
        }

        return dp[0][M];
    }

    private static int[][] randomMatrix(int len, int value) {
        int[][] matrix = new int[len << 1][2];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = (int) (Math.random() * value + 1);
            matrix[i][1] = (int) (Math.random() * value + 1);
        }
        return matrix;
    }

    public static void main(String[] args) {
        int N = 10;
        int value = 100;
        int testTime = 100;
        System.out.println("Test Begin!");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N + 1);
            int[][] matrix = randomMatrix(len, value);
            int ans1 = maxMoney1(matrix);
            int ans2 = maxMoney2(matrix);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("Test Finish!");
    }
}
