package com.jmc.algorithm.jjb.class41;

/**
 * 给定一个整型数组 arr，数组中的每个值都为正数，表示完成一幅画作需要的时间，再 给定 一个整数 num，表示画匠的数量，
 * 每个画匠只能画连在一起的画作。所有的画家 并行工作，请 返回完成所有的画作需要的最少时间。
 * <p>
 * 【举例】
 * arr=[3,1,4]，num=2。
 * 最好的分配方式为第一个画匠画 3 和 1，所需时间为 4。第二个画匠画 4，所需时间 为 4。 因为并行工作，所以最少时间为 4。
 * 如果分配方式为第一个画匠画 3，所需时 间为 3。第二个画 匠画 1 和 4，所需的时间为 5。那么最少时间为 5，显然没有第一 种分配方式好。所以返回 4。
 * <p>
 * arr=[1,1,1,4,3]，num=3。
 * 最好的分配方式为第一个画匠画前三个 1，所需时间为 3。第二个画匠画 4，所需时间 为 4。 第三个画匠画 3，所需时间为 3。返回 4。
 *
 * @author jmc
 * @version 1.0
 * @date 2021/3/28 20:13
 */
public class Code04_SplitArrayLargestSum {
    public static int split1(int[] arr, int n) {
        if (arr == null || n < 0) {
            return 0;
        }

        int[] sum = sum(arr);
        return process(0, arr.length - 1, n, arr, sum);
    }

    public static int process(int L, int R, int n, int[] arr, int[] sum) {
        if (L > R) {
            return 0;
        }
        if (R - L + 1 < n) {
            return getMax(arr, L, R);
        }
        if (n == 1) {
            return total(sum, L, R);
        }
        int next = Integer.MAX_VALUE;
        n = n - 1;
        for (int i = R; i >= 0; i--) {
            int split = process(L, i, n, arr, sum);
            if (i + 1 <= arr.length - 1) {
                split = Math.max(split, total(sum, i + 1, arr.length - 1));
            }
            next = Math.min(next, split);
        }
        return next;
    }

    public static int split2(int[] arr, int num) {
        if (arr == null || num < 0) {
            return 0;
        }

        int N = arr.length;
        int[] sum = sum(arr);
        int[][] dp = new int[N][num + 1];
        for (int i = 1; i <= num; i++) {
            dp[0][i] = arr[0];
        }
        for (int i = 1; i < N; i++) {
            dp[i][1] = total(sum, 0, i);
        }
        for (int j = 2; j <= num; j++) {
            for (int i = N - 1; i >= 0; i--) {
                int ans = Integer.MAX_VALUE;
                for (int leftEnd = 0; leftEnd <= i; leftEnd++) {
                    int leftCost = dp[leftEnd][j - 1];
                    int rightCost = leftEnd == N - 1 ? 0 : total(sum, leftEnd + 1, i);
                    int cur = Math.max(leftCost, rightCost);
                    if (cur < ans) {
                        ans = cur;
                    }
                }
                dp[i][j] = ans;
            }
        }

        return dp[N - 1][num];
    }

    public static int split3(int[] arr, int num) {
        if (arr == null || num < 0) {
            return 0;
        }

        int N = arr.length;
        int[] sum = sum(arr);
        int[][] dp = new int[N][num + 1];
        int[][] best = new int[N][num + 1];
        for (int i = 1; i <= num; i++) {
            dp[0][i] = arr[0];
            best[0][i] = -1;
        }
        for (int i = 0; i < N; i++) {
            dp[i][1] = total(sum, 0, i);
            best[i][1] = -1;
        }
        for (int j = 2; j <= num; j++) {
            for (int i = N - 1; i >= 1; i--) {
                int ans = Integer.MAX_VALUE;
                int choose = -1;
                int down = best[i][j - 1];
                int up = i == N - 1 ? N - 1 : best[i + 1][j];
                for (int leftEnd = down; leftEnd <= up; leftEnd++) {
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    int rightCost = leftEnd == i ? 0 : total(sum, leftEnd + 1, i);
                    int cur = Math.max(leftCost, rightCost);
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

    public static int[] sum(int[] arr) {
        int N = arr.length;
        int[] sum = new int[N + 1];
        sum[0] = 0;
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        return sum;
    }

    public static int total(int[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }

    public static int getMax(int[] arr, int L, int R) {
        int ans = Integer.MIN_VALUE;
        for (int i = L; i <= R; i++) {
            ans = Math.max(arr[i], ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        /*int[] arr = new int[]{1, 1, 1, 4, 3};
        int num = 3;*/
        /*int[] arr = new int[]{3, 1, 4};
        int num = 2;*/
        int[] arr = new int[]{7, 4, 9, 2, 9};
        int num = 3;
        System.out.println(split1(arr, num));
        System.out.println(split2(arr, num));
        System.out.println(split3(arr, num));
    }
}
