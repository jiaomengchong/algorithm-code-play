package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode-cn.com/problems/smallest-range-i/
 */
public class Problem_0908_SmallestRangeI {
    public static int smallestRangeI(int[] nums, int k) {
        // 输入：nums = [1,3,9], k = 3
        // 输出：0
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }

        int ans = 0;
        int min = nums[0];
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }

        ans = (max - min) <= (k << 1) ? 0 : max - min - (k << 1);

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 2};
        int k = 1;
        System.out.println(smallestRangeI(nums, k));
    }
}
