package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/build-array-from-permutation/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/14 15:29
 **/
public class Problem_1920_BuildArrayFromPermutation {
    public static int[] buildArray(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }

    // TODO 原地构建呢？
}
