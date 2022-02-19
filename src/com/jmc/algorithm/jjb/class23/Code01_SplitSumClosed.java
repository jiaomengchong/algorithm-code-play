package com.jmc.algorithm.jjb.class23;

/**
 * 给定一个正数数组arr，
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/20 9:05
 */
public class Code01_SplitSumClosed {
    public static int ways1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int N = arr.length;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
        }

        sum /=  2;
        return process(arr, 0, sum);
    }

    private static int process(int[] arr, int index, int rest) {
        if (arr.length == index) {
            return rest >= 0 ? 0 : -1;
        }

        int p1 = process(arr, index + 1, rest);
        int p2 = 0;
        int next = process(arr, index + 1, rest - arr[index]);
        if (next != -1 && rest >= arr[index]) {
            p2 = next + arr[index];
        }

        return Math.max(p1, p2);
    }

    public static int dpWays1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int sum = 0;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
        }
        sum /= 2;
        int[][] dp = new int[N + 1][sum + 1];
        for (int rest = 0; rest <= sum; rest++) {
            dp[N][rest] = 0;
        }
        for (int index = N -1; index >= 0; index--) {
            for (int rest = 0; rest <= sum; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                if (rest >= arr[index]) {
                    p2 = dp[index + 1][rest - arr[index]] + arr[index];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }

        return dp[0][sum];
    }

    public static void main(String[] args) {
        int[] arr = {11, 48, 0, 45, 43, 4, 18};
        System.out.println(ways1(arr));
        System.out.println(dpWays1(arr));
    }
}
