package com.jmc.algorithm.weekProblem.class_2022_02_4_week;

/**
 * 测试链接：https://leetcode-cn.com/problems/partition-array-for-maximum-sum/
 */
public class Code03_PartitionArrayForMaximumSum {
    public static int maxSumAfterPartitioning(int[] arr, int k) {
        // arr = [1,15,7,9,2,5,10], k = 3
        // 84
        return process(arr, 0, k);
    }

    private static int process(int[] arr, int index, int k) {
        if (index == arr.length) {
            return 0;
        }

        int max = arr[index];
        int ans = arr[index] + process(arr, index + 1, k);
        for (int i = index + 1; i < arr.length && (i - index + 1) <= k; i++) {
            max = Math.max(max, arr[i]);
            ans = Math.max(ans, max * (i - index + 1) + process(arr, i + 1, k));
        }
        return ans;
    }

    public static int maxSumAfterPartitioning2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;
        int[] dp = new int[n];
        dp[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = arr[i] + dp[i + 1];
            int max = arr[i];
            for (int j = i + 1; j < n && (j - i + 1) <= k; j++) {
                max = Math.max(max, arr[j]);
                dp[i] = Math.max(dp[i], (j == n - 1 ? 0 : dp[j + 1]) + (j - i + 1) * max);
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 15, 7, 9, 2, 5, 10};
        int k = 3;
        System.out.println(maxSumAfterPartitioning(arr, k));
        System.out.println(maxSumAfterPartitioning2(arr, k));
    }
}
