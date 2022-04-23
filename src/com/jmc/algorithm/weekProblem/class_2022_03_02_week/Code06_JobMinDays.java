package com.jmc.algorithm.weekProblem.class_2022_03_02_week;

import java.util.HashMap;
import java.util.Map;

/**
 * 来自微软
 * 给定一个正数数组arr，长度为N，依次代表N个任务的难度，给定一个正数k
 * 你只能从0任务开始，依次处理到N-1号任务结束，就是一定要从左往右处理任务
 * 只不过，难度差距绝对值不超过k的任务，可以在一天之内都完成
 * 返回完成所有任务的最少天数
 */
public class Code06_JobMinDays {
    public static int ways1(int[] arr, int k) {
        if (arr.length == 0 || arr == null) {
            return 0;
        }

        return process1(arr, arr.length - 1, k);
    }

    private static int process1(int[] arr, int index, int k) {
        if (index < 0) {
            return 0;
        }
        if (index == 0) {
            return 1;
        }
        int p1 = process1(arr, index - 1, k) + 1;
        int p2 = Integer.MAX_VALUE;
        int max = arr[index];
        int min = arr[index];
        for (int i = index - 1; i >= 0; i--) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
            if (max - min <= k) {
                p2 = Math.min(p2, 1 + process1(arr, i - 1, k));
            } else {
                break;
            }
        }
        return Math.min(p1, p2);
    }

    public static int ways2(int[] arr, int k) {
        if (arr.length == 0 || arr == null) {
            return 0;
        }

        int N = arr.length;
        Map<Integer, Integer> dp = new HashMap<>();
        for (int i = 0; i < N; i++) {
           dp.put(i, Integer.MAX_VALUE);
        }
        return process2(arr, N - 1, k, dp);
    }

    private static int process2(int[] arr, int index, int k, Map<Integer, Integer> dp) {
        if (index < 0) {
            return 0;
        }
        if (index == 0) {
            return 1;
        }
        if (dp.get(index) != Integer.MAX_VALUE) {
            return dp.get(index);
        }

        int p1 = process2(arr, index - 1, k, dp) + 1;
        int p2 = Integer.MAX_VALUE;
        int max = arr[index];
        int min = arr[index];
        for (int i = index - 1; i >= 0; i--) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
            if (max - min <= k) {
                p2 = Math.min(p2, 1 + process2(arr, i - 1, k, dp));
            }
        }
        int ans = Math.min(p1, p2);
        dp.put(index, ans);
        return ans;
    }

    public static int ways3(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            int p1 = dp[i - 1] + 1;
            int p2 = Integer.MAX_VALUE;
            int max = arr[i];
            int min = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                if (max - min <= k) {
                    p2 = Math.min(p2, 1 + (j - 1 >= 0 ? dp[j - 1] : 0));
                } else {
                    break;
                }
            }
            dp[i] = Math.min(p1, p2);
        }

        return dp[N - 1];
    }

    // TODO 优化:一维动态规划

    public static void main(String[] args) {
        int[] arr = new int[]{3, 7, 4, 7, 9};
        int k = 3;
        System.out.println(ways1(arr, k));
        System.out.println(ways2(arr, k));
        System.out.println(ways3(arr, k));
    }
}
