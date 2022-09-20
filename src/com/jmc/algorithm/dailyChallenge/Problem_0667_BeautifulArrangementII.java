package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/beautiful-arrangement-ii/
 */
public class Problem_0667_BeautifulArrangementII {
    public static int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        int index = 0;
        for (int i = 1; i < n - k; i++) {
            ans[index++] = i;
        }
        for (int i = n - k, j = n; i < n && i <= j; i++, j--) {
            ans[index++] = i;
            if (i != j) {
                ans[index++] = j;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 6, k = 4;
        System.out.println(Arrays.toString(constructArray(n, k)));
    }
}
