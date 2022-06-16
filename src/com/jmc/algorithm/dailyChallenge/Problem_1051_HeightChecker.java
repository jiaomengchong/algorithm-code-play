package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/height-checker/
 */
public class Problem_1051_HeightChecker {
    public static int heightChecker(int[] heights) {
        // heights = [1,1,4,2,1,3]
        // 3
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int N = heights.length;
        int[] copy = Arrays.copyOf(heights, N);
        Arrays.sort(copy);
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (copy[i] != heights[i]) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // heights = [5,1,2,3,4]
        // 5
        int[] heights = new int[]{1, 1, 4, 2, 1, 3};
        System.out.println(heightChecker(heights));
    }
}
