package com.jmc.algorithm.jjb.class21;

import java.util.Arrays;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/13 17:13
 */
public class Code02_CoinsWayEveryPaperDifferent {
    public static int coinWays1(int[] arr, int aim) {
        return process1(arr, 0, aim);
    }

    private static int process1(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        if (rest == 0) {
            return 1;
        }

        return process1(arr, index + 1, rest) + process1(arr, index + 1, rest - arr[index]);
    }

    public static int dpWays(int[] arr, int aim) {
        if (aim == 0) {
            return 1;
        }

        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 0; i < N + 1; i++) {
            dp[i][0] = 1;
        }
        for (int rest = 0; rest <= aim; rest++) {
            for (int index = N - 1; index >= 0; index--) {
                dp[index][rest] = dp[index + 1][rest] + ((rest - arr[index] >= 0) ? dp[index + 1][rest - arr[index]] : 0);
            }
        }

        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int aim = 12;
        System.out.println(coinWays1(arr, aim));
        System.out.println(dpWays(arr, aim));
    }
}
