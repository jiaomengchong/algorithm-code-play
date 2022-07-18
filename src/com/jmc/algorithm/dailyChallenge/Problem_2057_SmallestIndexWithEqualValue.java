package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/smallest-index-with-equal-value/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/18 15:19
 **/
public class Problem_2057_SmallestIndexWithEqualValue {
    public static int smallestEqual(int[] nums) {
        int ans = -1;
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            if (i % 10 == nums[i]) {
                ans = i;
                break;
            }
        }
        return ans;
    }
}
