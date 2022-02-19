package com.jmc.algorithm.greatOffer.class01;

import java.util.Arrays;

/**
 * 给定一个有序数组arr，代表坐落在X轴上的点
 * 给定一个正数K，代表绳子的长度
 * 返回绳子最多压中几个点？
 * 即使绳子边缘处盖住点也算盖住
 *
 * @author jmc
 * @version 1.0
 * @date 2021/4/22 9:39
 */
public class Code01_CordCoverMaxPoint {

    public static int maxPoint1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            int nearestIndex = nearestIndex(arr, i, arr[i] - k);
            res = Math.max(res, i - nearestIndex + 1);
        }
        return res;
    }

    private static int nearestIndex(int[] arr, int R, int value) {
        int ans = R;
        int L = 0;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        return ans;
    }

    public static int maxPoint2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        int left = 0;
        int right = 0;
        int ans = Integer.MIN_VALUE;
        while (left < N) {
            while (right < N && (arr[right] - arr[left] <= k)) {
                right++;
            }
            ans = Math.max(ans, right - left);
            left++;
        }
        return ans;
    }

    public static int[] generateArray(int maxValue, int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) {
        int maxLength = 100;
        int maxValue = 1000;
        int testTimes = 1000000;
        System.out.println("Test Begin");
        for (int i = 0; i < testTimes; i++) {
            int length = (int) (Math.random() * maxLength + 1);
            int[] arr = generateArray(maxValue, length);
            int K = (int) (Math.random() * maxValue);
            int ans1 = maxPoint1(arr, K);
            int ans2 = maxPoint2(arr, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("Test Finish!");
    }
}
