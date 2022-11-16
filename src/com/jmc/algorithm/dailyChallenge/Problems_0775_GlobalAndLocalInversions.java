package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/global-and-local-inversions/description/
 */
public class Problems_0775_GlobalAndLocalInversions {
    public static boolean isIdealPermutation(int[] nums) {
        int N = nums.length, min = nums[N - 1];
        for (int i = N - 3; i >= 0; i--) {
            if (nums[i] > min) {
                return false;
            }
            min = Math.min(min, nums[i + 1]);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 0};
        System.out.println(isIdealPermutation(nums));
    }
}
