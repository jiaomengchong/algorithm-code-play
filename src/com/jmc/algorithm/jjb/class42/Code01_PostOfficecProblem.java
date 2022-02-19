package com.jmc.algorithm.jjb.class42;

/**
 * 一条直线上有居民点，邮局只能建在居民点上。给定一个有序正数数组arr，每个值表示 居民点的一维坐标，再给定一个正数 num，表示邮局数量。
 * 选择num个居民点建立num个 邮局，使所有的居民点到最近邮局的总距离最短，返回最短的总距离
 * <p>
 * 【举例】
 * arr=[1,2,3,4,5,1000]，num=2。
 * 第一个邮局建立在 3 位置，第二个邮局建立在 1000 位置。那么 1 位置到邮局的距离 为 2， 2 位置到邮局距离为 1，3
 * 位置到邮局的距离为 0，4 位置到邮局的距离为 1， 5 位置到邮局的距 离为 2，1000 位置到邮局的距离为 0。这种方案下的总距离为 6，
 * 其他任何方案的总距离都不会 比该方案的总距离更短，所以返回6
 *
 * @author jmc
 * @version 1.0
 * @date 2021/3/30 8:55
 */
public class Code01_PostOfficecProblem {
    public static int bestMin1(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length <= num) {
            return 0;
        }

        int N = arr.length;
        int[][] w = new int[N + 1][N + 1];
        for (int L = 0; L < N; L++) {
            for (int R = L + 1; R < N; R++) {
                w[L][R] = w[L][R - 1] + arr[R] - arr[(L + R) >> 1];
            }
        }

        int[][] dp = new int[N][num + 1];
        for (int row = 0; row < N; row++) {
            dp[row][1] = w[0][row];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 2; j <= num; j++) {
                int ans = Integer.MAX_VALUE;
                for (int k = 0; k <= i; k++) {
                    ans = Math.min(ans, dp[k][j - 1] + w[k + 1][i]);
                }
                dp[i][j] = ans;
            }
        }
        return dp[N - 1][num];
    }

    public static int bestMin2(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length <= num) {
            return 0;
        }

        int N = arr.length;
        int[][] w = new int[N + 1][N + 1];
        for (int L = 0; L < N; L++) {
            for (int R = L + 1; R < N; R++) {
                w[L][R] = w[L][R - 1] + arr[R] - arr[(L + R) >> 1];
            }
        }

        int[][] dp = new int[N][num + 1];
        int[][] best = new int[N][num + 1];
        for (int row = 0; row < N; row++) {
            dp[row][1] = w[0][row];
            best[row][1] = -1;
        }

        for (int j = 2; j <= num; j++) {
            for (int i = N - 1; i >= 0; i--) {
                int down = best[i][j - 1];
                int up = i == N - 1 ? N - 1 : best[i + 1][j];
                int ans = Integer.MAX_VALUE;
                int choose = -1;
                for (int leftEnd = down; leftEnd <= up; leftEnd++) {
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    int rightCost = leftEnd == i ? 0 : w[leftEnd + 1][i];
                    int cur = leftCost + rightCost;
                    if (cur <= ans) {
                        ans = cur;
                        choose = leftEnd;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = choose;
            }
        }

        return dp[N - 1][num];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 1000};
        int num = 3;
        System.out.println(bestMin1(arr, num));
        System.out.println(bestMin2(arr, num));
    }
}
