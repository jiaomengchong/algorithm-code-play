package com.jmc.algorithm.jjb.class46;

/**
 * 戳气球
 * <p>
 * https://leetcode.com/problems/burst-balloons/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/4/10 21:07
 */
public class Code01_BurstBallons {
    public static int maxCoins0(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[] help = new int[N + 2];
        for (int i = 0; i < N; i++) {
            help[i + 1] = nums[i];
        }
        help[0] = 1;
        help[N + 1] = 1;

        return process0(help, 1, N);
    }

    private static int process0(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L - 1] * arr[L] * arr[R + 1];
        }

        int max = process0(arr, L + 1, R) + arr[L - 1] * arr[L] * arr[R + 1];
        max = Math.max(max, process0(arr, L, R - 1) + arr[L - 1] * arr[R] * arr[R + 1]);
        for (int i = L + 1; i < R; i++) {
            int left = process0(arr, L, i - 1);
            int right = process0(arr, i + 1, R);
            int last = arr[L - 1] * arr[i] * arr[R + 1];
            int cur = left + right + last;
            max = Math.max(max, cur);
        }

        return max;
    }

    public static int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int N = nums.length;
        int[] arr = new int[N + 2];
        for (int i = 0; i < N; i++) {
            arr[i + 1] = nums[i];
        }
        arr[0] = 1;
        arr[N + 1] = 1;
        int[][] dp = new int[N + 2][N + 2];
        dp[0][0] = 1;
        dp[N + 1][N + 1] = 1;
        for (int i = 1; i < N + 1; i++) {
            dp[i][i] = arr[i - 1] * arr[i] * arr[i + 1];
        }
        for (int L = N; L >= 1; L--) {
            for (int R = L + 1; R <= N; R++) {
                int max = dp[L + 1][R] + arr[L - 1] * arr[L] * arr[R + 1];
                max = Math.max(max, dp[L][R - 1] + arr[L - 1] * arr[R] * arr[R + 1]);
                for (int i = L + 1; i < R; i++) {
                    int left = dp[L][i - 1];
                    int right = dp[i + 1][R];
                    int last = arr[L - 1] * arr[i] * arr[R + 1];
                    int cur = left + right + last;
                    max = Math.max(max, cur);
                }
                dp[L][R] = max;
            }
        }
        return dp[1][N];
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{8, 2, 6, 8, 9, 8, 1, 4, 1, 5, 3, 0, 7, 7, 0, 4, 2, 2, 5, 5};
        int[] nums = new int[]{3, 1, 5, 8};
        System.out.println(maxCoins0(nums));
        System.out.println(maxCoins(nums));
    }
}
