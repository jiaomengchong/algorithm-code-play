package com.jmc.algorithm.jjb.class23;

/**
 * 给定一个正数数组arr,请把arr中所有的数分成两个集合
 * 如果arr长度为偶数,两个集合包含数的个数要一样多
 * 如果arr长度为奇数,两个集合包含数的个数必须只差一个
 * 请尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下,较小集合的累加和
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/20 12:13
 */
public class Code02_SplitSumClosedSizeHalf {
    public static int ways1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int sum = 0;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
        }

        sum = sum >> 1;

        if ((N & 1) == 0) {
            return process1(arr, 0, N >> 1, sum);
        } else {
            return Math.max(process1(arr, 0, N >> 1, sum), process1(arr, 0, (N >> 1) + 1, sum));
        }
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
        sum = sum >> 1;
        int pickSize = (N >> 1) + 1;
        int[][][] dp = new int[N + 1][pickSize + 1][sum + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= pickSize; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        for (int rest = 0; rest <= sum; rest++) {
            dp[N][0][rest] = 0;
        }

        for (int index = N - 1; index >= 0; index--) {
            for (int picks = 0; picks <= pickSize; picks++) {
                for (int rest = 0; rest <= sum; rest++) {
                    int p1 = dp[index + 1][picks][rest];
                    int p2 = -1;
                    int next = -1;
                    if (arr[index] <= rest && picks >= 1) {
                        next = dp[index + 1][picks - 1][rest - arr[index]];
                    }
                    if (next != -1) {
                        p2 = next + arr[index];
                    }
                    dp[index][picks][rest] = Math.max(p1, p2);
                }
            }
        }

        if ((N & 1) == 0) {
            return dp[0][N >> 1][sum];
        } else {
            return Math.max(dp[0][N >> 1][sum], dp[0][(N >> 1) + 1][sum]);
        }
    }

    private static int process1(int[] arr, int index, int picks, int rest) {
        if (arr.length == index) {
            return picks == 0 ? 0 : -1;
        }

        int p1 = process1(arr, index + 1, picks, rest);
        int p2 = -1;
        int next = -1;
        if (arr[index] <= rest) {
            next = process1(arr, index + 1, picks - 1, rest - arr[index]);
        }
        if (next != -1) {
            p2 = next + arr[index];
        }
        return Math.max(p1, p2);
    }

    public static void main(String[] args) {
        // result = 52
        int[] arr = {7, 3, 25, 42, 36, 38, 5};
        System.out.println(ways1(arr));
        System.out.println(dpWays1(arr));
    }
}
