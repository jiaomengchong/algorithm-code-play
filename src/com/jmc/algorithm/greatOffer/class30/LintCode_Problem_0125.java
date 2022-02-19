package com.jmc.algorithm.greatOffer.class30;

/**
 * @author jmc
 * @version 1.0
 * @date 2021/8/18 19:03
 */
public class LintCode_Problem_0125 {
    public static int backPackII(int bag, int[] weights, int[] values) {
        if (bag < 0 || weights == null || weights.length != values.length) {
            return 0;
        }

        return func1(weights, values, 0, bag);
    }

    private static int func1(int[] weights, int[] values, int index, int rest) {
        if (rest < 0) {
            return Integer.MIN_VALUE;
        }

        // weights=[1, 2, 4, 3]
        //          0  1  2  3
        if (index == weights.length) {
            return 0;
        }

        int p1 = func1(weights, values, index + 1, rest);
        int next = func1(weights, values, index + 1, rest - weights[index]);
        int p2 = 0;
        if (next != Integer.MIN_VALUE) {
            p2 = next + values[index];
        }

        return Math.max(p1, p2);
    }

    public static int backPackII2(int bag, int[] weights, int[] values) {
        if (bag < 0 || weights == null || weights.length != values.length) {
            return 0;
        }

        int[][] dp = new int[weights.length + 1][bag + 1];
        for (int i = 0; i <= weights.length; i++) {
            for (int j = 0; j <= bag; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        return func2(weights, values, 0, bag, dp);
    }

    private static int func2(int[] weights, int[] values, int index, int rest, int[][] dp) {
        if (rest < 0) {
            return Integer.MIN_VALUE;
        }

        if (dp[index][rest] != Integer.MIN_VALUE) {
            return dp[index][rest];
        }

        // weights=[1, 2, 4, 3]
        //          0  1  2  3
        if (index == weights.length) {
            return 0;
        }

        int p1 = func2(weights, values, index + 1, rest, dp);
        int next = func2(weights, values, index + 1, rest - weights[index], dp);
        int p2 = 0;
        if (next != Integer.MIN_VALUE) {
            p2 = next + values[index];
        }

        dp[index][rest] = Math.max(p1, p2);
        return Math.max(p1, p2);
    }

    public static int backPackII3(int bag, int[] weights, int[] values) {
        if (bag < 0 || weights == null || weights.length != values.length) {
            return 0;
        }

        int N = weights.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = (rest - weights[index] < 0) ? 0 : dp[index + 1][rest - weights[index]] + values[index];
                dp[index][rest] = Math.max(p1, p2);
            }
        }

        return dp[0][bag];
    }

    public static void main(String[] args) {
        int m = 10;
        int[] A = new int[]{2, 3, 5, 7};
        int[] V = new int[]{1, 5, 2, 4};
        System.out.println(backPackII(m, A, V));
        System.out.println(backPackII2(m, A, V));
        System.out.println(backPackII3(m, A, V));
    }
}
