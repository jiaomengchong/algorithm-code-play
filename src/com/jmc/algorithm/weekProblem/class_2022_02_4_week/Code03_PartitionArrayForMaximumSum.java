package com.jmc.algorithm.weekProblem.class_2022_02_4_week;

/**
 * 测试链接：https://leetcode-cn.com/problems/partition-array-for-maximum-sum/
 */
public class Code03_PartitionArrayForMaximumSum {
    public static int maxSumAfterPartition(int[] arr, int k) {
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

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 1, 5, 7, 3, 6, 1, 9, 9, 3};
        int k = 4;
        System.out.println(maxSumAfterPartition(arr, k));
    }
}
