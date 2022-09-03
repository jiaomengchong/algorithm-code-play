package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/
 */
public class Problem_1343_NumberOfSubArraysOfSizeKAndAverageGreaterThanOrEqualToThreshold {
    public static int numOfSubarrays(int[] arr, int k, int threshold) {
        int ans = 0;
        int N = arr.length;
        if (N < k) {
            return ans;
        }

        int[] preSum = new int[N];
        preSum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            preSum[i] = arr[i] + preSum[i - 1];
        }

        int compare = k * threshold;
        int left = -1, right = 0;
        for (int i = 0; i < N; i++) {
            if (right < N && right - left == k) {
                if ((left == -1 && preSum[right] >= compare) || (left >= 0 && preSum[right] - preSum[left] >= compare)) {
                    ans++;
                }
                left++;
            }
            right++;
        }
        return ans;
    }

    public static int numOfSubarrays2(int[] arr, int k, int threshold) {
        int N = arr.length, ans = 0;
        int left = 0, right = 0, sum = arr[0];
        int compare = k * threshold;
        for (int i = 0; i < N; i++) {
            while (right < N && right - left != k - 1) {
                right++;
                sum += right < N ? arr[right] : 0;
            }
            if (sum >= compare) {
                ans++;
            }
            sum -= arr[left++];
            if (right == N - 1) {
                return ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // [2,2,2,2,5,5,5,8]
        // 3
        // 4
        int[] arr = new int[]{2,2,2,2,5,5,5,8};
        int k = 3, threshold = 4;
        System.out.println(numOfSubarrays(arr, k, threshold));
        System.out.println(numOfSubarrays2(arr, k, threshold));
    }
}
