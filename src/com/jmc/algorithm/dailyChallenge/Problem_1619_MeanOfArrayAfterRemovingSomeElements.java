package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/mean-of-array-after-removing-some-elements/
 */
public class Problem_1619_MeanOfArrayAfterRemovingSomeElements {
    public static double trimMean(int[] arr) {
        double ans = 0, sum = 0.0;
        int N = arr.length;
        int delCnt = (int) (N * (1.0 / 20));
        Arrays.sort(arr);
        for (int i = delCnt; i < N - delCnt; i++) {
            sum += arr[i];
        }
        ans = sum / (N - delCnt * 2);
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 7, 8, 7, 7, 8, 4, 4, 6, 8, 8, 7, 6, 8, 8, 9, 2, 6, 0, 0, 1, 10, 8, 6, 3, 3, 5, 1, 10, 9, 0, 7, 10, 0, 10, 4, 1, 10, 6, 9, 3, 6, 0, 0, 2, 7, 0, 6, 7, 2, 9, 7, 7, 3, 0, 1, 6, 1, 10, 3};
        System.out.println(trimMean(arr));
    }
}
