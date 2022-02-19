package com.jmc.algorithm.jjb.class41;

import java.util.Arrays;

/**
 * 给定一个非负数组arr，长度为N，
 * 那么有N-1种方案可以把arr切成左右两部分
 * 每一种方案都有，min{左部分累加和，右部分累加和}
 * 求这么多方案中，min{左部分累加和，右部分累加和}的最大值是多少？
 * 整个过程要求时间复杂度O(N)
 *
 * @author jmc
 * @version 1.0
 * @date 2021/3/24 17:42
 */
public class Code01_BestSplitForAll {
    public static int bestSplit1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int ans = 0;
        for (int s = 0; s < N - 1; s++) {
            int sumL = 0;
            int sumR = 0;
            for (int l = 0; l <= s; l++) {
                sumL += arr[l];
            }
            for (int r = s + 1; r < N; r++) {
                sumR += arr[r];
            }
            ans = Math.max(ans, Math.min(sumL, sumR));
        }
        return ans;
    }

    public static int bestSplit2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int N = arr.length;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
        }

        int sumL = 0;
        int ans = 0;
        for (int i = 0; i < N - 1; i++) {
            sumL += arr[i];
            int sumR = sum - sumL;
            ans = Math.max(ans, Math.min(sumL, sumR));
        }
        return ans;
    }

    public static int[] generateArray(int max, int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    public static void main(String[] args) {
        int max = 20;
        int maxLength = 10;
        int testTime = 1000000;
        System.out.println("test begin!");
        for (int i = 0; i < testTime; i++) {
            int length = (int) (Math.random() * maxLength);
            int[] arr = generateArray(max, length);
            int ans1 = bestSplit1(arr);
            int ans2 = bestSplit2(arr);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                System.out.println(Arrays.toString(arr));
            }
        }
        System.out.println("Finish!");
    }
}
