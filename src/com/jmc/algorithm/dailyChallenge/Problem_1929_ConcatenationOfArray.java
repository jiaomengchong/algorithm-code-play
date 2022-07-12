package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/concatenation-of-array/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/12 17:32
 **/
public class Problem_1929_ConcatenationOfArray {
    public static int[] getConcatenation(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N << 1];
        for (int i = 0; i < N; i++) {
            ans[i] = nums[i];
            ans[N + i] = nums[i];
        }
        return ans;
    }
}
