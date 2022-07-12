package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/find-numbers-with-even-number-of-digits/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/12 18:21
 **/
public class Problem_1295_FindNumbersWithEvenNumberOfDigits {
    public static int findNumbers(int[] nums) {
        int N = nums.length;
        int ans = 0;
        for (int num : nums) {
            ans += (String.valueOf(num).length() & 1) == 0 ? 1 : 0;
        }
        return ans;
    }
}
