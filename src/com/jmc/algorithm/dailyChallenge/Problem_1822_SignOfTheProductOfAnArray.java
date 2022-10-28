package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/sign-of-the-product-of-an-array/
 */
public class Problem_1822_SignOfTheProductOfAnArray {
    public static int arraySign(int[] nums) {
        int negCnt = 0;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            }
            negCnt += num < 0 ? 1 : 0;
        }
        return (negCnt % 2) == 0 ? 1 : -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{9, 72, 34, 29, -49, -22, -77, -17, -66, -75, -44, -30, -24};
        System.out.println(arraySign(nums));
    }
}
