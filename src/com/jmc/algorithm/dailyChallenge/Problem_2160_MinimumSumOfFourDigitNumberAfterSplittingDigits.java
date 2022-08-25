package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-sum-of-four-digit-number-after-splitting-digits/
 */
public class Problem_2160_MinimumSumOfFourDigitNumberAfterSplittingDigits {
    public static int minimumSum(int num) {
        int ans = 0;
        int[] queue = new int[4];
        int[] base = new int[]{1000, 100, 10, 1};
        int index = 0;
        for (int i = 0; i < 4; i++) {
            queue[index++] = num / base[i];
            num %= base[i];
        }
        Arrays.sort(queue);
        ans = queue[0] * 10 + queue[2] + queue[1] * 10 + queue[3];
        return ans;
    }

    public static void main(String[] args) {
        int num = 4009;
        System.out.println(minimumSum(num));
    }
}
