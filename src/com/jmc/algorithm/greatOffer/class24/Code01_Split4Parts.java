package com.jmc.algorithm.greatOffer.class24;

/**
 * @author jmc
 * @version 1.0
 * @date 2021/7/31 22:17
 */
public class Code01_Split4Parts {
    public static boolean canSplits(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }

        // [3, 2, 3, 7,  4,  4,  3,  1,  1,  6,  7,  1,  5,  2]
        // [   3, 5, 8,  15, 19, 23, 26, 27, 28, 34, 41, 42, 47, 49]
        // [0, 1, 2, 3,  4,  5,  6,  7 , 8,  9,  10, 11, 12, 13]
        int N = arr.length;
        int sum = arr[0];
        for (int i = 1; i < N; i++) {

        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 3, 7, 4, 4, 3, 1, 1, 6, 7, 1, 5, 2};
        System.out.println(canSplits(arr));
    }
}
