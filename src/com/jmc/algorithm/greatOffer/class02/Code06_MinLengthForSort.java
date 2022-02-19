package com.jmc.algorithm.greatOffer.class02;

/**
 * 给定一个数组arr，只能对arr中的一个子数组排序，
 * 但是想让arr整体都有序
 * 返回满足这一设定的子数组中，最短的是多长
 *
 * @author jmc
 * @version 1.0
 * @date 2021/5/6 11:40
 */
public class Code06_MinLengthForSort {
    public static int minLength(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int N = arr.length;
        int noMaxIndex = -1;
        int max = arr[0];
        for (int i = 1; i < N; i++) {
            if (max > arr[i]) {
                noMaxIndex = i;
            } else {
                max = Math.max(max, arr[i]);
            }
        }

        int noMinIndex = N - 1;
        int min = arr[N - 1];
        for (int i = N - 1; i >= 0; i--) {
            if (min < arr[i]) {
                noMinIndex = i;
            } else {
                min = Math.min(min, arr[i]);
            }
        }
        return noMaxIndex - noMinIndex + 1;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 };
        System.out.println(minLength(arr));
    }
}
