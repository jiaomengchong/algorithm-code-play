package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/find-n-unique-integers-sum-up-to-zero/
 */
public class Problem_1304_FindNUniqueIntegersSumUpToZero {
    public static int[] sumZero(int n) {
        if (n == 2) {
            return new int[]{-1, 1};
        }

        int[] ans = new int[n];
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            ans[i] = i;
            sum += i;
        }
        ans[n - 1] = -sum;
        return ans;
    }

    public static void main(String[] args) {
        int n = 1;
        System.out.println(Arrays.toString(sumZero(n)));
    }
}
