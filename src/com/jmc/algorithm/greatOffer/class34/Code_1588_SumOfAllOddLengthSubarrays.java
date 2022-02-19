package com.jmc.algorithm.greatOffer.class34;

import java.util.Arrays;

/**
 * @author jmc
 * @version 1.0
 * @date 2021/8/29 16:10
 */
public class Code_1588_SumOfAllOddLengthSubarrays {
    public static int sumOddLengthSubarrays(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int ans = 0;
        int N = arr.length;
        for (int start = 0; start < N; start++) {
            for (int length = 1; start + length <= N; length += 2) {
                int end = start + length - 1;
                for (int i = start; i <= end; i++) {
                    ans += arr[i];
                }
            }
        }

        return ans;
    }

    public static int sumOddLengthSubarrays1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int ans = 0;
        int N = arr.length;
        int[] preSum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            preSum[i + 1] = preSum[i] + arr[i];
        }

        for (int start = 0; start < N; start++) {
            for (int length = 1; start + length <= N; length += 2) {
                ans += preSum[length + start] - preSum[start];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 2, 5, 3};
        System.out.println(sumOddLengthSubarrays(arr));
        System.out.println(sumOddLengthSubarrays1(arr));
    }
}
