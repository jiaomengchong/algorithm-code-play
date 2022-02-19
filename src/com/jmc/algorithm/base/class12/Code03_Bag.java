package com.jmc.algorithm.base.class12;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/8 16:19
 */
public class Code03_Bag {

    public static int process1(int[] weight, int[] value, int bag) {
        if (weight.length == 0 || value.length == 0 || bag < 0) {
            return 0;
        }

        return f(weight, value, 0, bag);
    }

    public static int f(int[] weight, int[] value, int index, int rest) {

        if (rest < 0) {
            return -1;
        }

        if (index == weight.length) {
            return 0;
        }

        int p1 = f(weight, value, index + 1, rest);
        int p2 = -1;
        int p2Next = f(weight, value, index + 1, rest - weight[index]);
        if (p2Next != -1) {
            p2 = value[index] + p2Next;
        }

        return Math.max(p1, p2);
    }

    public static int dp(int[] w, int[] v, int bag) {
        if (bag < 0) {
            return -1;
        }

        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = -1;
                if (rest - w[index]>=0) {
                    int p2Next = dp[index + 1][ rest - w[index]];
                    p2 = v[index] + p2Next;
                }

                dp[index][rest] = Math.max(p1,p2);
            }
        }

        return dp[0][bag];

    }

    public static void main(String[] args) {
        int[] weight = {2, 5, 6, 17};
        int[] value = {1, 2, 3, 4};
        int bag = 23;
        System.out.println(process1(weight, value, bag));
        System.out.println(dp(weight, value, bag));

    }
}
