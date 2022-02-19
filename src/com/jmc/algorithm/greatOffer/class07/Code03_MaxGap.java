package com.jmc.algorithm.greatOffer.class07;

/**
 * 给定一个数组arr，
 * 返回如果排序之后，相邻两数的最大差值
 * 要求：时间复杂度O(N)
 *
 * @author jmc
 * @version 1.0
 * @date 2021/5/19 14:40
 */
public class Code03_MaxGap {
    public static int maxGap(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int N = arr.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            max = Math.max(arr[i], max);
            min = Math.min(arr[i], min);
        }

        if (min == max) {
            return 0;
        }

        // 准备N + 1个桶
        int[] maxBucket = new int[N + 1];
        int[] minBucket = new int[N + 1];
        boolean[] hasBucket = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            int bid = getBucket(arr[i], N, min, max);
            maxBucket[bid] = hasBucket[bid] ? Math.max(arr[i], maxBucket[bid]) : arr[i];
            minBucket[bid] = hasBucket[bid] ? Math.min(arr[i], minBucket[bid]) : arr[i];
            hasBucket[bid] = true;
        }

        int ans = Integer.MIN_VALUE;
        int preMax = maxBucket[0];
        for (int i = 1; i <= N; i++) {
            if (hasBucket[i]) {
                ans = Math.max(ans, minBucket[i] - preMax);
                preMax = maxBucket[i];
            }
        }
        return ans;
    }

    private static int getBucket(int index, int size, int min, int max) {
        return (index - min) * size / (max - min);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{56, 8};
        System.out.println(maxGap(arr));
    }
}
