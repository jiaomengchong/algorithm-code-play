package com.jmc.algorithm.jjb.class22;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/18 14:52
 */
public class Code02_MinCoinsNoLimit {
    public static int ways1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 1) {
            return 0;
        }

        return process1(arr, 0, aim);
    }

    private static int process1(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        } else {
            int ans = Integer.MAX_VALUE;
            for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                int next = process1(arr, index + 1, rest - zhang * arr[index]);
                if (next != Integer.MAX_VALUE) {
                    ans = Math.min(ans, zhang + next);
                }
            }
            return ans;
        }
    }

    public static int dpWays1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 1) {
            return 0;
        }

        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int rest = 1; rest <= aim; rest++) {
            dp[N][rest] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    int next = dp[index + 1][rest - zhang * arr[index]];
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, zhang + next);
                    }
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][aim];
    }

    public static int dpWays2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 1) {
            return 0;
        }

        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int rest = 1; rest <= aim; rest++) {
            dp[N][rest] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0 && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
                }
            }
        }
        return dp[0][aim];
    }


    public static void main(String[] args) {
        int[] arr = {2, 9, 11, 29, 6, 5, 7, 23, 30, 26, 1, 14};
        int aim = 56;
        System.out.println(ways1(arr, aim));
        System.out.println(dpWays1(arr, aim));
        System.out.println(dpWays2(arr, aim));
    }
}
