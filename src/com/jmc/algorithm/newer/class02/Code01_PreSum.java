package com.jmc.algorithm.newer.class02;

import java.util.Arrays;

/**
 * 假设有一个数组arr，用户总是频繁的查询arr中某一段的累加和
 * 你如何组织数据，能让这种查询变得便利和快捷？
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/17 17:16
 */
public class Code01_PreSum {

    public static int[] rangeSum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        int N = arr.length;
        int[] preSum = new int[N];

        preSum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }

        return preSum;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 4, 1, 6, 2, 8};
        System.out.println(Arrays.toString(rangeSum2(arr)));
    }
}
