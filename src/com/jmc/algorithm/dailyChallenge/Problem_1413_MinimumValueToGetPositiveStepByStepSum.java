package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-value-to-get-positive-step-by-step-sum/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/9 10:26
 **/
public class Problem_1413_MinimumValueToGetPositiveStepByStepSum {
    public static int minStartValue(int[] nums) {
        int ans = 1;
        int preSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (preSum < 0) {
                ans = Math.max(ans, -preSum + 1);
            }
            preSum += nums[i];
        }
        if (preSum < 0) {
            ans = Math.max(ans, -preSum + 1);
        }
        return ans;
    }
}
