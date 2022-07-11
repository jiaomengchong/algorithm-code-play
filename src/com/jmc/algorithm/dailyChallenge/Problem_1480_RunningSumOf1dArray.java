package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/running-sum-of-1d-array/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/11 14:44
 **/
public class Problem_1480_RunningSumOf1dArray {
    public static int[] runningSum(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        ans[0] = nums[0];
        for (int i = 1; i < N; i++) {
            ans[i] = ans[i - 1] + nums[i];
        }
        return ans;
    }
}
