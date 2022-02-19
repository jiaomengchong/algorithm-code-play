package com.jmc.algorithm.greatOffer.class43;

import java.util.Arrays;

/**
 * 360
 * 给定一个正数数组arr，长度为n，下标0~n-1
 * arr中的0、n-1位置不需要达标，它们分别是最左、最右的位置
 * 中间位置i需要达标，达标的条件是 : arr[i-1] > arr[i] 或者 arr[i+1] > arr[i]哪个都可以
 * 你每一步可以进行如下操作：对任何位置的数让其-1
 * 你的目的是让arr[1~n-2]都达标，这时arr称之为yeah！数组
 * 返回至少要多少步可以让arr变成yeah！数组
 * 数据规模 : 数组长度 <= 10000，数组中的值<=500
 *
 * @Author jmc
 * @Description
 * @Date 2021/11/4 16:42
 **/
public class Code02_MinCostToYeahArray {
    public static final Integer INVALID = Integer.MAX_VALUE;

    public static int ways1(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length < 3) {
            return 0;
        }

        int N = arr.length;
        int min = arr[0];
        for (int i = 1; i < N; i++) {
            min = Math.min(min, arr[i]);
        }

        for (int i = 0; i < N; i++) {
            arr[i] += +N - min;
        }

        return processWays1(arr, 1, arr[0], true);
    }

    private static int processWays1(int[] arr, int index, int pre, boolean preOk) {
        if (index == arr.length - 1) {
            return preOk || pre < arr[index] ? 0 : Integer.MAX_VALUE;
        }

        int ans = Integer.MAX_VALUE;
        if (preOk) {
            for (int cur = arr[index]; cur >= 0; cur--) {
                int p1 = processWays1(arr, index + 1, cur, cur < pre);
                if (p1 != Integer.MAX_VALUE) {
                    ans = Math.min(ans, p1 + arr[index] - cur);
                }
            }
        } else {
            // 4 7 index
            // 7没被搞定
            for (int cur = arr[index]; cur > pre; cur--) {
                int next = processWays1(arr, index + 1, cur, false);
                if (next != Integer.MAX_VALUE) {
                    ans = Math.min(ans, next + arr[index] - cur);
                }
            }
        }
        return ans;
    }

    public static int ways2(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length < 3) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] += arr.length - min;
        }

        int N = arr.length;
        // 初始化dp
        int[][][] dp = new int[N][2][];
        for (int i = 1; i < N; i++) {
            dp[i][0] = new int[arr[i - 1] + 1];
            dp[i][1] = new int[arr[i - 1] + 1];
            Arrays.fill(dp[i][0], Integer.MAX_VALUE);
            Arrays.fill(dp[i][1], Integer.MAX_VALUE);
        }

        for (int pre = 0; pre <= arr[N - 2]; pre++) {
            dp[N - 1][0][pre] = pre < arr[N - 1] ? 0 : Integer.MAX_VALUE;
            dp[N - 1][1][pre] = 0;
        }

        for (int index = N - 2; index >= 1; index--) {
            for (int pre = 0; pre <= arr[index - 1]; pre++) {
                // pre没被搞定
                for (int cur = arr[index]; cur > pre; cur--) {
                    int next = dp[index + 1][0][cur];
                    if (next != Integer.MAX_VALUE) {
                        dp[index][0][pre] = Math.min(dp[index][0][pre], next + arr[index] - cur);
                    }
                }
                // pre被搞定了
                for (int cur = arr[index]; cur >= 0; cur--) {
                    int next = dp[index + 1][cur < pre ? 1 : 0][cur];
                    if (next != Integer.MAX_VALUE) {
                        dp[index][1][pre] = Math.min(dp[index][1][pre], next + arr[index] - cur);
                    }
                }
            }
        }

        return dp[1][1][arr[0]];
    }

    public static int ways3(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length < 3) {
            return 0;
        }

        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] += arr.length - min;
        }

        int N = arr.length;
        int[][][] dp = new int[N][2][];
        for (int i = 1; i < N; i++) {
            dp[i][1] = new int[arr[i - 1] + 1];
            dp[i][0] = new int[arr[i - 1] + 1];
        }

        for (int pre = 0; pre <= arr[N - 2]; pre++) {
            dp[N - 1][0][pre] = pre < arr[N - 1] ? 0 : Integer.MAX_VALUE;
        }

        // best辅助数组
        int[][] best = best(dp, N - 1, arr[N - 2]);
        for (int index = N - 2; index >= 1; index--) {
            for (int pre = 0; pre <= arr[index - 1]; pre++) {
                if (arr[index] < pre) {
                    dp[index][1][pre] = best[1][arr[index]];
                } else {
                    dp[index][1][pre] = Math.min(best[0][pre], pre > 0 ? best[1][pre - 1] : Integer.MAX_VALUE);
                }
                dp[index][0][pre] = arr[index] <= pre ? Integer.MAX_VALUE : best[0][pre + 1];
            }
            best = best(dp, index, arr[index - 1]);
        }

        return dp[1][1][arr[0]];
    }

    private static int[][] best(int[][][] dp, int index, int value) {
        int[][] best = new int[2][value + 1];
        best[0][value] = dp[index][0][value];
        for (int pre = value - 1; pre >= 0; pre--) {
            best[0][pre] = dp[index][0][pre] == Integer.MAX_VALUE ? Integer.MAX_VALUE : (value - pre + dp[index][0][pre]);
            best[0][pre] = Math.min(best[0][pre], best[0][pre + 1]);
        }

        best[1][0] = dp[index][1][0] == Integer.MAX_VALUE ? Integer.MAX_VALUE : (value + dp[index][1][0]);
        for (int pre = 1; pre <= value; pre++) {
            best[1][pre] = dp[index][1][pre] == Integer.MAX_VALUE ? Integer.MAX_VALUE : (dp[index][1][pre] + value - pre);
            best[1][pre] = Math.min(best[1][pre], best[1][pre - 1]);
        }

        return best;
    }

    // 最优解dp左+dp右
    public static int _yeah(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length < 3) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        int N = arr.length;
        int[] copyLeft = Arrays.copyOf(arr, N);
        int[] copyRight = Arrays.copyOf(arr, N);
        int[] dpLeft = new int[N];
        int[] dpRight = new int[N];
        dpLeft[0] = 0;
        dpRight[N - 1] = 0;

        for (int i = 1; i < N; i++) {
            dpLeft[i] += dpLeft[i - 1];
            if (i != N - 1 && arr[i] >= copyLeft[i - 1]) {
                dpLeft[i] += arr[i] - copyLeft[i - 1] + 1;
                copyLeft[i] = copyLeft[i - 1] - 1;
            }
        }

        for (int i = N - 2; i >= 0; i--) {
            dpRight[i] += dpRight[i + 1];
            if (i != 0 && arr[i] >= copyRight[i + 1]) {
                dpRight[i] += arr[i] - copyRight[i + 1] + 1;
                copyRight[i] = copyRight[i + 1] - 1;
            }
        }

        for (int i = 0; i < N - 1; i++) {
            ans = Math.min(ans, dpLeft[i] + dpRight[i + 1]);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{7, 3, 9, 10, 7, 5, 8, 7, 3, 9, 10, 7, 5, 8};
        System.out.println(_yeah(arr));
        System.out.println(ways1(arr));
        System.out.println(ways2(arr));
        System.out.println(ways3(arr));
    }
}
