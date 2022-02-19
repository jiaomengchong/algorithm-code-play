package com.jmc.algorithm.jjb.class41;

import java.util.Arrays;

import static com.jmc.algorithm.jjb.class41.Code01_BestSplitForAll.generateArray;

/**
 * 摆放着n堆石子。现要将石子有次序地合并成一堆
 * 规定每次只能选相邻的2堆石子合并成新的一堆，
 * 并将新的一堆石子数记为该次合并的得分
 * 求出将n堆石子合并成一堆的最小得分（或最大得分）合并方案
 *
 * @author jmc
 * @version 1.0
 * @date 2021/3/27 20:54
 */
public class Code03_StoneMerge {
    public static int min1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process(0, arr.length - 1, sum(arr));
    }

    public static int process(int L, int R, int[] sum) {
        if (L == R) {
            return 0;
        }
        int next = Integer.MAX_VALUE;
        for (int leftEnd = L; leftEnd < R; leftEnd++) {
            next = Math.min(next, process(L, leftEnd, sum) + process(leftEnd + 1, R, sum));
        }
        return next + total(L, R, sum);
    }

    public static int min2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int N = arr.length;
        int[] sum = sum(arr);
        int[][] dp = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            dp[i][i + 1] = total(i, i + 1, sum);
        }

        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                int next = Integer.MAX_VALUE;
                for (int leftEnd = L; leftEnd < R; leftEnd++) {
                    next = Math.min(next, dp[L][leftEnd] + dp[leftEnd + 1][R]);
                }
                dp[L][R] = next + total(L, R, sum);
            }
        }

        return dp[0][N - 1];
    }

    public static int min3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int N = arr.length;
        int[][] dp = new int[N][N];
        int[] sum = sum(arr);
        int[][] best = new int[N][N];

        for (int i = 0; i < N - 1; i++) {
            dp[i][i + 1] = total(i, i + 1, sum);
            best[i][i + 1] = i;
        }

        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                int next = Integer.MAX_VALUE;
                int chose = -1;
                for (int leftEnd = best[L][R - 1]; leftEnd <= best[L + 1][R]; leftEnd++) {
                    int cur = dp[L][leftEnd] + dp[leftEnd + 1][R];
                    if (cur <= next) {
                        chose = leftEnd;
                        next = cur;
                    }
                }
                dp[L][R] = next + total(L, R, sum);
                best[L][R] = chose;
            }
        }
        return dp[0][N - 1];
    }

    public static int[] sum(int[] arr) {
        int N = arr.length;
        int[] sum = new int[N + 1];
        sum[0] = 0;
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        return sum;
    }

    public static int total(int l, int r, int[] sum) {
        return sum[r + 1] - sum[l];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 1, 2, 3};
        System.out.println(Arrays.toString(sum(arr)));
        System.out.println(total(1, 3, sum(arr)));
        System.out.println(min1(arr));
        System.out.println(min2(arr));
        System.out.println(min3(arr));

        int max = 20;
        int maxLength = 10;
        int testTime = 100000;
        System.out.println("Test Begin!");
        for (int i = 0; i < testTime; i++) {
            int length = (int) (Math.random() * maxLength);
            int[] array = generateArray(max, length);
            int ans1 = min1(array);
            int ans2 = min2(array);
            int ans3 = min3(array);
            if (ans1 != ans2 || ans3 != ans1) {
                System.out.println("Oops!");
            }
        }
        System.out.println("Test Finish!");
    }
}
