package com.jmc.algorithm.jjb.class21;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 * 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/15 16:11
 */
public class Code03_CoinsWayNoLimit {
    public static int coinsWay1(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return process1(arr, 0, aim);
    }

    private static int process1(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }

        int ways = 0;
        for (int zhang = 0; arr[index] * zhang <= rest; zhang++) {
            ways += process1(arr, index + 1, rest - arr[index] * zhang);
        }

        return ways;
    }

    public static int dpWays1(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; arr[index] * zhang <= rest; zhang++) {
                    ways += dp[index + 1][rest - arr[index] * zhang];
                }
                dp[index][rest] = ways;
            }
        }

        return dp[0][aim];
    }

    public static int dpWays2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // 斜率优化
                dp[index][rest] = (rest - arr[index] >= 0 ? dp[index][rest - arr[index]] : 0) + dp[index + 1][rest];
            }
        }

        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {19, 1, 11, 21, 22, 27, 3, 4, 16};
        int aim = 28;

        System.out.println(coinsWay1(arr, aim));
        System.out.println(dpWays1(arr, aim));
        System.out.println(dpWays2(arr, aim));
    }
}
