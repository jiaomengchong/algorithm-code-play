package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/minimize-maximum-pair-sum-in-array/submissions/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/1 15:34
 **/
public class Problem_1877_MinimizeMaximumPairSumInArray {
    public static int minPairSum(int[] nums) {
        int ans = Integer.MIN_VALUE, left = 0, right = nums.length - 1;
        Arrays.sort(nums);
        while (left < right) {
            ans = Math.max(ans, nums[left++] + nums[right--]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 4, 2, 4, 6};
        System.out.println(minPairSum(arr));
    }
}
