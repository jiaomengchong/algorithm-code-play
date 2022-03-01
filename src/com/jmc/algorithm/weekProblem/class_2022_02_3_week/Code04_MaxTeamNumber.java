package com.jmc.algorithm.weekProblem.class_2022_02_3_week;

import java.util.Arrays;

/**
 * 来自微软
 * 给定一个数组arr，一个正数num，一个正数k
 * 可以把arr中的某些数字拿出来组成一组，要求该组中的最大值减去最小值<=num
 * 且该组数字的个数一定要正好等于k
 * 每个数字只能选择进某一组，不能进多个组
 * 返回arr中最多有多少组
 *
 * @Author jmc
 * @Description
 * @Date 2022/3/1 18:55
 **/
public class Code04_MaxTeamNumber {
    public static int ways1(int[] arr, int num, int k) {
        // arr sort
        Arrays.sort(arr);
        return process(arr, num, k, 0);
    }

    private static int process(int[] arr, int num, int k, int index) {
        if (index == arr.length) {
            return 0;
        }

        // 不要index位置
        int p1 = process(arr, num, k, index + 1);
        int p2 = process(arr, num, k, index + 1);
        if (index >= k - 1) {
            p2 += arr[index] - arr[index - k + 1] <= num ? 1 : 0;
        }
        return Math.max(p1, p2);
    }

    public static int ways2(int[] arr, int num, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        int[] dp = new int[n];
        dp[k - 1] = arr[k - 1] - arr[0] <= num ? 1 : 0;
        for (int i = k; i < n; i++) {
            int p1 = dp[i - 1];
            int p2 = (arr[i] - arr[i - k + 1] <= num ? 1 : 0) + dp[i - k];
            dp[i] = Math.max(p1, p2);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 12, 1, 33, 14, 15, 7, 63, 5, 62, 9, 8, 7};
        int num = 2;
        int k = 2;
        System.out.println(ways1(arr, num, k));
        System.out.println(ways2(arr, num, k));
    }
}
