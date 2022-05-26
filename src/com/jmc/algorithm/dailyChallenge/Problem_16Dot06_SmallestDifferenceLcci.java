package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/smallest-difference-lcci/
 */
public class Problem_16Dot06_SmallestDifferenceLcci {
    public static int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int len1 = a.length, len2 = b.length;
        int i = 0, j = 0;
        int ans = Integer.MAX_VALUE;
        while (i < len1 && j < len2) {
            if (a[i] == b[j]) {
                return 0;
            } else {
                if ((a[i] == Integer.MIN_VALUE || b[j] == Integer.MIN_VALUE) &&
                        (a[i] < 0 || b[j] < 0)) {
                    ans = Integer.MAX_VALUE;
                } else {
                    ans = Math.min(ans, Math.abs(a[i] - b[j]));
                }
                int pos = a[i] > b[j] ? j++ : i++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        // [-2147483648,1]
        // [2147483647,0]
        int[] a = new int[]{-2147483648,1};
        int[] b = new int[]{2147483647,0};
        System.out.println(smallestDifference(a, b));
    }
}
